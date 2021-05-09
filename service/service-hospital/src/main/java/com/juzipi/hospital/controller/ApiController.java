package com.juzipi.hospital.controller;

import com.juzipi.commonutil.constant.MongoConstants;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.tool.ResultTools;
import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.hospital.service.ScheduleService;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.serviceutil.util.HttpRequestHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @Autowired
    private ScheduleService scheduleService;



    @ApiOperation(value = "上传医院")
    @PostMapping("saveHospital")
    public Result insertHospital(HttpServletRequest request){
        //获取参数，再通过工具类把String[]数组转换为Object类型
        Map<String, Object> map = getParameterMap(request);
        Object hospitalLogoData = map.get(MongoConstants.LOGO_DATA);
        //说什么base编码后会将 + 转换为 空格，这里是给它再转换还回去
        String logoData = hospitalLogoData.toString().replace(" ", "+");
        map.put("logoData", logoData);
        //根据医院code查询数据库进行sign的比对
        if (checkSign(map)) {
            return judgmentResult(hospitalService.insertHospital(map));
        }
        return ResultTools.failData("签名不一致");
    }


    @ApiOperation(value = "查询医院")
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request){
        Map<String, Object> map = getParameterMap(request);
        Object hospitalCode = map.get(MongoConstants.HP_CODE);
        //同样的验证签名是否一致
        if (checkSign(map)) {
            Hospital hospital = hospitalService.queryHospitalByHpCode(hospitalCode);
            return judgmentResult(hospital);
        }
        return ResultTools.failData("签名不一致");
    }



    @ApiOperation(value = "上传科室")
    @PostMapping("saveDepartment")
    public Result insertDepartment(HttpServletRequest request){
        //获取参数，再通过工具类把String[]数组转换为Object类型
        Map<String, Object> map = getParameterMap(request);
        if (checkSign(map)){
            return judgmentResult(departmentService.insertDepartment(map));
        }
        return ResultTools.failData("签名错误");
    }


    /**
     * 查询科室
     * @param request
     * @return
     */
    @PostMapping("department/list")
    public Result getDepartment(HttpServletRequest request){
        Map<String, Object> parameterMap = getParameterMap(request);
        //输入page值为空就赋给它一个默认值，不为空就获取page值
        Integer pageNum = Integer.parseInt(parameterMap.get(MongoConstants.PAGE).toString());
        Integer pageSize = Integer.parseInt(parameterMap.get(MongoConstants.LIMIT).toString());
        String hpCode = parameterMap.get(MongoConstants.HP_CODE).toString();
        //也就是pageNum和pageSize,把操作放到service
        /*
        他的加密逻辑是这样的，有问题，应该只加密23ce3b89e0a80072a57a4977582610dd这个数据就行了，有时间了研究研究改改
        加密前：100|10|1|1620374817541|23ce3b89e0a80072a57a4977582610dd
        加密后：80823b1961b7e5204969e8754255feda
         */
        return judgmentResult(departmentService.queryPageDepartment(pageNum, pageSize, hpCode));
    }


    /**
     * 删除科室
     * @param request
     * @return
     */
    @ApiOperation(value = "根据id删除科室")
    @PostMapping("department/remove")
    public Result deleteDepartment(HttpServletRequest request){
        Map<String, Object> parameterMap = getParameterMap(request);
        String hoCode = parameterMap.get(MongoConstants.HP_CODE).toString();
        String depCode = parameterMap.get(MongoConstants.DEP_CODE).toString();
        return toResult(departmentService.removeDepartment(hoCode, depCode));
    }


    /**
     * 上传排班
     * @param request
     * @return
     */
    @ApiOperation(value = "上传排班")
    @PostMapping("saveSchedule")
    public Result insertSchedule(HttpServletRequest request){
        Map<String, Object> parameterMap = getParameterMap(request);
//        if (checkSign(parameterMap)){
        return judgmentResult(scheduleService.insertSchedule(parameterMap));
//        }
//        return ResultTools.failData("签名错误");
    }



    @ApiOperation(value = "查询排班")
    @PostMapping("schedule/list")
    public Result getSchedule(HttpServletRequest request){
        //跟科室一样的操作
        Map<String, Object> parameterMap = getParameterMap(request);
        Integer pageNum = Integer.parseInt(parameterMap.get(MongoConstants.PAGE).toString());
        Integer pageSize = Integer.parseInt(parameterMap.get(MongoConstants.LIMIT).toString());
        String hpCode = parameterMap.get(MongoConstants.HP_CODE).toString();
        return judgmentResult(scheduleService.queryPageSchedule(pageNum, pageSize, hpCode));
    }


    @ApiOperation(value = "删除排班")
    @PostMapping("schedule/remove")
    public Result deleteSchedule(HttpServletRequest request){
        Map<String, Object> parameterMap = getParameterMap(request);
        String hpCode = parameterMap.get(MongoConstants.HP_CODE).toString();
        String hpScheduleId = parameterMap.get(MongoConstants.HP_SCHEDULE_ID).toString();
        return toResult(scheduleService.removeSchedule(hpCode, hpScheduleId));
    }




















    /**
     * 封装成方法吧，获取请求参数
     * @param request
     * @return
     */
    private Map<String, Object> getParameterMap(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        return HttpRequestHelper.switchMap(parameterMap);
    }


    /**
     * 签名校验
     * @param parameterMap
     * @return
     */
    private Boolean checkSign(Map<String, Object> parameterMap) {
        Object hospitalSign = parameterMap.get(MongoConstants.SIGN);
        Object hospitalCode = parameterMap.get(MongoConstants.HP_CODE);
        return hospitalService.compareHospitalSign(hospitalSign, hospitalCode);
    }

}


