package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.repository.HospitalRepository;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/4 21:28
 * @Info
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;


    @Override
    public Integer insertHospital(Map<String, Object> map) {
        //先转为String, 再用parseObject方法传入相应的参数
        String string = JSONObject.toJSONString(map);
        Hospital hospitalEntity = JSONObject.parseObject(string, Hospital.class);
        Hospital hospital = hospitalRepository.queryHospitalByHpCode(hospitalEntity.getHpCode());
        //查询数据库的到的不为空表示已存在此数据就是更新
        if (StringUtils.isNull(hospital)){
            hospitalRepository.
            hospital.setStatus(hospital.getStatus());
            hospital.setCreateTime(hospital.getCreateTime());

        }

    }
}
