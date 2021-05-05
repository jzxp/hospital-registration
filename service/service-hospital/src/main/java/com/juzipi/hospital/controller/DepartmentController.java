package com.juzipi.hospital.controller;

import com.juzipi.commonutil.constant.ManageConstants;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.serviceutil.util.HttpRequestHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
