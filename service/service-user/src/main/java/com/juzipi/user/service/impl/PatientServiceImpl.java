package com.juzipi.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.inter.model.pojo.user.Patient;
import com.juzipi.user.mapper.PatientMapper;
import com.juzipi.user.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/6/1 16:53
 * @Info
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper,Patient> implements PatientService {


    @Override
    public List<Patient> findAll(Long userId) {
        return null;
    }
}
