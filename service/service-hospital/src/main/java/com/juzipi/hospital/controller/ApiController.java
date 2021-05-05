package com.juzipi.hospital.controller;

import com.juzipi.commonutil.constant.HospitalConstants;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.tool.ResultTools;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.serviceutil.util.HttpRequestHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class ApiController extends BaseController {

    @Autowired
    private HospitalService hospitalService;


    @PostMapping("saveHospital")
    public Result createHospital(HttpServletRequest request){
        //获取参数，再通过工具类把String[]数组转换为Object类型
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> map = HttpRequestHelper.switchMap(parameterMap);
        //获取医院签名，签名MD5加密过
        Object hospitalSign = map.get(HospitalConstants.SIGN);
        Object hospitalCode = map.get(HospitalConstants.HP_CODE);
        Object hospitalLogoData = map.get(HospitalConstants.LOGO_DATA);
        //说什么base编码后会将+转换为空格，这里是给它再转换还回去
        String logoData = hospitalLogoData.toString().replace(" ", "+");
        map.put("logoData", logoData);
        //根据医院code查询数据库进行sign的比对
        if (hospitalService.compareHospitalSign(hospitalSign, hospitalCode)) {
            return judgmentResult(hospitalService.insertHospital(map));
            
        }
        return ResultTools.failData("签名不一致");
    }

}
