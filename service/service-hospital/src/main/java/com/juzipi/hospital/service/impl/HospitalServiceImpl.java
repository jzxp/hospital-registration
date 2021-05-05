package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juzipi.commonutil.constant.BaseConstants;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.repository.HospitalRepository;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Boolean insertHospital(Map<String, Object> map) {
        //先转为String, 再用parseObject方法 传入相应的参数转为hospitalEntity对象
        String string = JSONObject.toJSONString(map);
        Hospital hospitalEntity = JSONObject.parseObject(string, Hospital.class);

        Hospital hospital = hospitalRepository.queryHospitalByHpCode(hospitalEntity.getHpCode());
        //查询数据库的到的不为空表示已存在此数据就是更新
        if (StringUtils.isNull(hospital)){
            //为空就是新增
            hospital.setStatus(ConstantsMp.STATUS_VALUE_MONGO);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(hospital.getCreateTime());
            hospital.setDeleted(ConstantsMp.DELETED_VALUE);
            Hospital insert = hospitalRepository.insert(hospital);
            return checkHospitalExists(insert.getHpCode());
        }
        hospital.setStatus(hospital.getStatus());
        hospital.setCreateTime(hospital.getCreateTime());
        hospital.setUpdateTime(hospital.getUpdateTime());
        hospital.setDeleted(hospital.getDeleted());
        Hospital save = hospitalRepository.save(hospital);
        return checkHospitalExists(save.getHpCode());
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
