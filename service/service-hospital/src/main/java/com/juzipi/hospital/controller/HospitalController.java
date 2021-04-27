package com.juzipi.hospital.controller;

import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.pojo.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/4/27 14:35
 * @Info
 */
@RestController
@RequestMapping("hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;


    @GetMapping("list")
    public List<Hospital> selectHospitalList(){
        //直接调用IService的list方法
        return hospitalService.list();
    }

}
