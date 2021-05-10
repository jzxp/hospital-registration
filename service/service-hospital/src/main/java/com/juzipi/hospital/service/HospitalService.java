package com.juzipi.hospital.service;

import com.github.pagehelper.PageInfo;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import com.juzipi.inter.vo.hospital.HospitalSelectVo;

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


    /**
     * 判断签名是否正确（一致）
     * @param hospitalSign
     * @param hospitalCode
     * @return
     */
    Boolean compareHospitalSign(Object hospitalSign, Object hospitalCode);

    /**
     * 根据医院编号查询医院
     * @param hospitalCode
     * @return
     */
    Hospital queryHospitalByHpCode(Object hospitalCode);

    PageInfo<Hospital> queryHospitalPage(PageBody pageBody, HospitalSelectVo hospitalSelectVo);

    Hospital updateHospitalStatus(String id, Integer status);
}
