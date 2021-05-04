package com.juzipi.hospital.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.hospital.repository.HospitalRepository;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.serviceutil.util.HttpRequestHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/4 21:30
 * @Info
 */

@Api(tags = "接口管理")
@RestController
@RequestMapping("api/hosp")
public class ApiController {

    @Autowired
    private HospitalService hospitalService;


    @PostMapping("saveHospital")
    public Result createHospital(HttpServletRequest request){
        //获取参数，再通过工具类把String[]数组转换为Object类型
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        hospitalService.insertHospital(map);
    }

}
