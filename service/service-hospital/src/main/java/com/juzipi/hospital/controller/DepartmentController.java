package com.juzipi.hospital.controller;

import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.serviceutil.core.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author juzipi
 * @Date 2021/5/5 19:51
 * @Info
 */
@RestController
@RequestMapping()
public class DepartmentController extends BaseController {


    @Autowired
    private DepartmentService departmentService;



}
