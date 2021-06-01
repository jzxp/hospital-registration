package com.juzipi.user.api;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.util.AuthContextHolder;
import com.juzipi.user.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author juzipi
 * @Date 2021/6/1 16:52
 * @Info
 */
@RestController
@RequestMapping
public class PatientApiController {


    @Autowired
    private PatientService patientService;


    @GetMapping("auth/findAll")
    public Result findAll(HttpServletRequest request){
        patientService.findAll(AuthContextHolder.getUserId(request))
    }



}
