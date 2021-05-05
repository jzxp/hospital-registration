package com.juzipi.hospital.controller;

import com.juzipi.commonutil.constant.ManageConstants;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.tool.ResultTools;
import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.serviceutil.util.HttpRequestHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
//这里是医院上传相关的操作，全写在一起了，比较乱
@Api(tags = "接口管理")
@RestController
@RequestMapping("api/hosp")
public class ApiController extends BaseController {


    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DepartmentService departmentService;



    @ApiOperation(value = "上传医院")
    @PostMapping("saveHospital")
    public Result insertHospital(HttpServletRequest request){
        //获取参数，再通过工具类把String[]数组转换为Object类型
        Map<String, Object> map = getParameterMap(request);
        //获取医院签名，签名MD5加密过
        Object hospitalSign = map.get(ManageConstants.SIGN);
        Object hospitalCode = map.get(ManageConstants.HP_CODE);
        Object hospitalLogoData = map.get(ManageConstants.LOGO_DATA);
        //说什么base编码后会将 + 转换为 空格，这里是给它再转换还回去
        String logoData = hospitalLogoData.toString().replace(" ", "+");
        map.put("logoData", logoData);
        //根据医院code查询数据库进行sign的比对
        if (hospitalService.compareHospitalSign(hospitalSign, hospitalCode)) {
            return judgmentResult(hospitalService.insertHospital(map));
        }
        return ResultTools.failData("签名不一致");
    }


    @ApiOperation(value = "查询医院")
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request){
        Map<String, Object> map = getParameterMap(request);
        Object hospitalCode = map.get(ManageConstants.HP_CODE);
        Object hospitalSign = map.get(ManageConstants.SIGN);
        //同样的验证签名是否一致
        if (hospitalService.compareHospitalSign(hospitalSign, hospitalCode)) {
            return judgmentResult(hospitalService.queryHospitalByHpCode(hospitalCode));
        }
        return ResultTools.failData("签名不一致");
    }



    @ApiOperation(value = "上传科室")
    @PostMapping("saveDepartment")
    public Result insertDepartment(HttpServletRequest request){
        //获取参数，再通过工具类把String[]数组转换为Object类型
        Map<String, Object> map = getParameterMap(request);
        Object hospitalSign = map.get(ManageConstants.SIGN);
        Object hospitalCode = map.get(ManageConstants.HP_CODE);

        if (hospitalService.compareHospitalSign(hospitalSign, hospitalCode)){
            return judgmentResult(departmentService.insertDepartment(map));
        }
        return ResultTools.failData("签名错误");
    }






    /**
     * 封装成方法吧，获取请求参数
     * @param request
     * @return
     */
    protected Map<String, Object> getParameterMap(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        return HttpRequestHelper.switchMap(parameterMap);
    }

}
