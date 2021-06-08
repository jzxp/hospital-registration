package com.juzipi.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzipi.inter.model.pojo.user.Patient;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/6/1 16:53
 * @Info
 */
public interface PatientService extends IService<Patient> {


    List<Patient> findAll(Long userId);


    Patient getPatientById(Long id);


}
