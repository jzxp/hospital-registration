package com.juzipi.hospital.service;

import com.juzipi.inter.model.pojo.hospital.Hospital;

import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/4 21:27
 * @Info
 */
public interface HospitalService {

    /**
     * 上传医院接口
     * @param map
     * @return
     */
    Hospital insertHospital(Map<String, Object> map);


    Boolean compareHospitalSign(Object hospitalSign, Object hospitalCode);
}
