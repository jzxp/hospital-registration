package com.juzipi.hospital.service;

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
    Boolean insertHospital(Map<String, Object> map);



}
