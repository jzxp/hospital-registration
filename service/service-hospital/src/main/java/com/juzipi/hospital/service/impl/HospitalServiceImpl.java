package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.mapper.HospitalSetMapper;
import com.juzipi.hospital.repository.HospitalRepository;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import com.juzipi.inter.model.pojo.hospital.HospitalSet;
import com.juzipi.serviceutil.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @Author juzipi
 * @Date 2021/5/4 21:28
 * @Info
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HospitalSetMapper hospitalSetMapper;


    @Override
    public Hospital insertHospital(Map<String, Object> map) {
        //先转为String, 再用parseObject方法 传入相应的参数转为hospitalEntity对象
        String string = JSONObject.toJSONString(map);
        Hospital hospital = JSONObject.parseObject(string, Hospital.class);

        //查询数据库的到的不为空表示已存在此数据就是更新
        if (checkHospitalExists(hospital.getHpCode())){
            //不为空就是更新
            hospital.setStatus(hospital.getStatus());
            hospital.setCreateTime(hospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setDeleted(hospital.getDeleted());
            //重复添加会报错，这个新增更新的操作也不透明，看不到它干了什么
            return hospitalRepository.save(hospital);
        }
        //为空就是新增
        hospital.setCreateTime(new Date());
        hospital.setStatus(ConstantsMp.STATUS_VALUE_MONGO);
        hospital.setUpdateTime(hospital.getCreateTime());
        hospital.setDeleted(ConstantsMp.DELETED_VALUE);
        return hospitalRepository.save(hospital);
    }


    @Override
    public Boolean compareHospitalSign(Object hospitalSign, Object hospitalCode) {
        /*
        注意：hospitalSet表里的signKey并没有加密，它只是随机生成的字符串
         */
        HospitalSet hospitalSet = hospitalSetMapper.selectOne(new QueryWrapper<HospitalSet>().lambda().select(HospitalSet::getSignKey).eq(HospitalSet::getHpCode, hospitalCode));
        String signKey = hospitalSet.getSignKey();
        //加密一下然后比较
        String encryptSignKey = MD5.encrypt(signKey);
        return Objects.equals(encryptSignKey, hospitalSign);
    }


    /**
     * 根据hpCode判断hospital是否存在
     * @param hpCode
     * @return 存在就是true，否则false
     */
    private Boolean checkHospitalExists(String hpCode){
        Hospital hospital = hospitalRepository.queryHospitalByHpCode(hpCode);
        return StringUtils.isNotNull(hospital);
    }

}
