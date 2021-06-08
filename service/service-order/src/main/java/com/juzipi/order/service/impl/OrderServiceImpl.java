package com.juzipi.order.service.impl;

import com.alibaba.excel.event.Order;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.hospital.client.HospitalFeignClient;
import com.juzipi.inter.model.pojo.order.OrderInfo;
import com.juzipi.inter.model.pojo.order.SignInfoVo;
import com.juzipi.inter.model.pojo.user.Patient;
import com.juzipi.inter.vo.hospital.ScheduleOrderVo;
import com.juzipi.order.mapper.OrderMapper;
import com.juzipi.order.service.OrderService;
import com.juzipi.user.client.PatientFeginClient;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        return null;
    }


}
