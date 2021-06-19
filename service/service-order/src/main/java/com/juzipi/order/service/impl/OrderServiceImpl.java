package com.juzipi.order.service.impl;

import com.alibaba.excel.event.Order;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.util.conversion.OrderInfoStatusConversion;
import com.juzipi.hospital.client.HospitalFeignClient;
import com.juzipi.inter.model.pojo.order.OrderInfo;
import com.juzipi.inter.model.pojo.order.SignInfoVo;
import com.juzipi.inter.model.pojo.user.Patient;
import com.juzipi.inter.vo.hospital.ScheduleOrderVo;
import com.juzipi.inter.vo.order.OrderMqVo;
import com.juzipi.inter.vo.order.OrderSelectVo;
import com.juzipi.inter.vo.sms.SmsVo;
import com.juzipi.order.mapper.OrderMapper;
import com.juzipi.order.service.OrderService;
import com.juzipi.rabbit.constant.MqConstants;
import com.juzipi.rabbit.service.RabbitService;
import com.juzipi.serviceutil.util.HttpRequestHelper;
import com.juzipi.user.client.PatientFeginClient;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

/**
 * @Author juzipi
 * @Date 2021/6/6 21:57
 * @Info
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {

    @Autowired
    private PatientFeginClient patientFeginClient;
    @Autowired
    private HospitalFeignClient hospitalFeignClient;
    @Autowired
    private RabbitService rabbitService;


    @ApiOperation("保存订单")
    @Override
    public Long saveOrder(String scheduleId, Long patientId) {
        //获取就诊人信息
        Patient patient = (Patient) patientFeginClient.getPatientOrder(patientId).getData();
        //获取排班相关信息
        ScheduleOrderVo scheduleOrderVo = (ScheduleOrderVo) hospitalFeignClient.getScheduleOrderVo(scheduleId).getData();
        //判断当前时间是否还可以预约
        if (new DateTime(scheduleOrderVo.getStartTime()).isAfterNow()
                || new DateTime(scheduleOrderVo.getEndTime()).isBeforeNow()){
            throw new BaseException(this.getClass().getName(),"已经不能再预约了");
        }
        SignInfoVo signInfoVo = (SignInfoVo) hospitalFeignClient.getSognInfoVo(scheduleOrderVo.getHpCode()).getData();
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(scheduleOrderVo,orderInfo);
        //给orderInfo设置其他数据
        String outTradeNo = System.currentTimeMillis() + ""+ new Random().nextInt(100);
        orderInfo.setOutTradeNo(outTradeNo);
        orderInfo.setScheduleId(scheduleId);
        orderInfo.setUserId(patient.getUserId());
        orderInfo.setPatientName(patient.getName());
        orderInfo.setPatientPhone(patient.getPhoneNumber());
        orderInfo.setOrderStatus(0);
        baseMapper.insert(orderInfo);

        //调用医院接口。设置调用医院接口需要的参数，放到map集合
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("hpCode",orderInfo.getHpCode());
        paramMap.put("depCode",orderInfo.getDepCode());
        paramMap.put("hosScheduleId",orderInfo.getScheduleId());
        paramMap.put("reserveDate",new DateTime(orderInfo.getReserveDate()).toString("yyyy-MM-dd"));
        paramMap.put("reserveTime", orderInfo.getReserveTime());
        paramMap.put("amount",orderInfo.getAmount());

        paramMap.put("name", patient.getName());
        paramMap.put("certificatesType",patient.getCertificatesType());
        paramMap.put("certificatesNo", patient.getCertificatesNo());
        paramMap.put("gender",patient.getGender());
        paramMap.put("birthday", patient.getBirthday());
        paramMap.put("phoneNumber",patient.getPhoneNumber());
        paramMap.put("isMarry", patient.getIsMarry());
        paramMap.put("provinceCode",patient.getProvinceCode());
        paramMap.put("cityCode", patient.getCityCode());
        paramMap.put("districtCode",patient.getDistrictCode());
        paramMap.put("address",patient.getAddress());
        //联系人
        paramMap.put("contactsName",patient.getContactsName());
        paramMap.put("contactsCertificatesType", patient.getContactsCertificatesType());
        paramMap.put("contactsCertificatesNo",patient.getContactsCertificatesNo());
        paramMap.put("contactsPhoneNumber",patient.getContactsPhoneNumber());
        paramMap.put("timestamp", HttpRequestHelper.getTimestamp());
        String sign = HttpRequestHelper.getSign(paramMap, signInfoVo.getSignKey());
        paramMap.put("sign",sign);
        JSONObject result = HttpRequestHelper.sendRequest(paramMap, signInfoVo.getApiUrl() + "/order/submitOrder");
        if (result.getInteger("code") == 200){
            JSONObject jsonObject = result.getJSONObject("data");
            String hosRecordId = jsonObject.getString("hpRecordId");
            //预约序号
            Integer number = jsonObject.getInteger("number");;
            //取号时间
            String fetchTime = jsonObject.getString("fetchTime");;
            //取号地址
            String fetchAddress = jsonObject.getString("fetchAddress");;
            //更新订单
            orderInfo.setHpRecordId(hosRecordId);
            orderInfo.setNumber(number);
            orderInfo.setFetchTime(fetchTime);
            orderInfo.setFetchAddress(fetchAddress);
            baseMapper.updateById(orderInfo);
            //排班可预约数
            Integer reservedNumber = jsonObject.getInteger("reservedNumber");
            //排班剩余预约数
            Integer availableNumber = jsonObject.getInteger("availableNumber");
            //发送rabbitMQ了,号源更新短信通知
            OrderMqVo orderMqVo = new OrderMqVo();
            orderMqVo.setScheduleId(scheduleId);
            orderMqVo.setReservedNumber(reservedNumber);
            orderMqVo.setAvailableNumber(availableNumber);
            //短信提示
            SmsVo smsVo = new SmsVo();
            smsVo.setPhone(orderInfo.getPatientPhone());
            String reserveDate = new DateTime(orderInfo.getReserveDate()).toString("yyyy-MM-dd") + (orderInfo.getReserveTime() == 0 ? "上午" : "下午");
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("title",orderInfo.getHpName()+"|"+orderInfo.getDepName()+"|"+orderInfo.getTitle());
            hashMap.put("amount",orderInfo.getAmount());
            hashMap.put("reserveDate",reserveDate);
            hashMap.put("name",orderInfo.getPatientName());
            hashMap.put("quitTime",new DateTime(orderInfo.getQuitTime()).toString("yyyy-MM-dd HH:mm"));
            smsVo.setParam(hashMap);
            orderMqVo.setSmsVo(smsVo);
            //发送
            rabbitService.sendMessage(MqConstants.EXCHANGE_DIRECT_ORDER, MqConstants.ROUTING_ORDER,orderMqVo);

        }else {
            throw new BaseException(this.getClass().getName(),500,"保存订单信息失败啦");
        }
        return orderInfo.getId();
    }


    @Override
    public OrderInfo getOrder(String orderId) {
        OrderInfo orderInfo = baseMapper.selectById(orderId);
        orderInfo.getParam().put("orderStatusString", OrderInfoStatusConversion.stateTransition(orderInfo.getOrderStatus()));
        return orderInfo;
    }


    @Override
    public void getPage(Long pageNum, Long pageSize, OrderSelectVo orderSelectVo) {
        //医院名称
        String keyword = orderSelectVo.getKeyword();
        //就诊人名称
        Long patientId = orderSelectVo.getPatientId();
        //订单状态
        String orderStatus = orderSelectVo.getOrderStatus();
        //安排时间
        String reserveDate = orderSelectVo.getReserveDate();
        //开始时间
        String createTimeBegin = orderSelectVo.getCreateTimeBegin();
        //结束时间
        String createTimeEnd = orderSelectVo.getCreateTimeEnd();

        LambdaQueryWrapper<OrderInfo> wrapper = new QueryWrapper<OrderInfo>().lambda().like(OrderInfo::getHpName, keyword)
                .eq(OrderInfo::getPatientId, patientId)
                .eq(OrderInfo::getOrderStatus, orderStatus)
                .eq(OrderInfo::getReserveDate, reserveDate)
                .eq(OrderInfo::getCreateTime, createTimeBegin)
                .eq(OrderInfo::getCreateTime, createTimeEnd);
        Page<OrderInfo> pageInfo = baseMapper.selectPage(new Page<OrderInfo>(), wrapper);


    }


}
