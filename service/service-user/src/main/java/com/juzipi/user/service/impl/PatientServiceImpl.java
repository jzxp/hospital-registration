package com.juzipi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.client.dict.DictFeignClient;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.inter.model.pojo.user.Patient;
import com.juzipi.user.mapper.PatientMapper;
import com.juzipi.user.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/6/1 16:53
 * @Info
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper,Patient> implements PatientService {


    @Autowired
    private DictFeignClient dictFeignClient;


    @Override
    public List<Patient> findAll(Long userId) {
        List<Patient> patients = baseMapper.selectList(new QueryWrapper<Patient>().lambda().eq(Patient::getUserId, userId));
        patients.forEach(this::setPatient);
        return patients;
    }



    @Override
    public Patient getPatientById(Long id) {
        return this.setPatient(baseMapper.selectById(id));
    }



    private Patient setPatient(Patient patient) {
        //证件类型
        Result certificatesType = dictFeignClient.getName("CertificatesType", patient.getCertificatesType());
        //联系人证件类型
        Result contactsCertificatesType = dictFeignClient.getName("CertificatesType", patient.getContactsCertificatesType());
        //省
        Result provinceCode = dictFeignClient.getName(patient.getProvinceCode());
        //市
        Result cityCode = dictFeignClient.getName(patient.getCityCode());
        //区
        Result districtCode = dictFeignClient.getName(patient.getDistrictCode());

        patient.getParam().put("certificatesType",certificatesType.getData());
        patient.getParam().put("contactsCertificatesType",contactsCertificatesType.getData());
        patient.getParam().put("provinceCode",provinceCode.getCode());
        patient.getParam().put("cityCode",cityCode.getCode());
        patient.getParam().put("districtCode",districtCode.getCode());
        return patient;
    }

}
