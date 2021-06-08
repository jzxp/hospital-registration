package com.juzipi.user.api;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.util.AuthContextHolder;
import com.juzipi.inter.model.pojo.user.Patient;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.user.service.PatientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author juzipi
 * @Date 2021/6/1 16:52
 * @Info
 */
@RestController
@RequestMapping("api/user/patient")
public class PatientApiController extends BaseController {


    @Autowired
    private PatientService patientService;


    @ApiOperation("获取就诊人列表")
    @GetMapping("auth/findAll")
    public Result findAll(HttpServletRequest request){
        return judgmentResult(patientService.findAll(AuthContextHolder.getUserId(request)));
    }


    @ApiOperation("添加就诊人")
    @PostMapping("auth/save")
    public Result savePatient(@RequestBody Patient patient,HttpServletRequest request){
        patient.setUserId(AuthContextHolder.getUserId(request));
        return judgmentResult(patientService.save(patient));
    }


    @ApiOperation("获取就诊人信息")
    @GetMapping("auth/get/{id}")
    public Result getPatient(@PathVariable Long id){
        return judgmentResult(patientService.getPatientById(id));
    }


    @ApiOperation("修改就诊人")
    @PutMapping("auth/update")
    public Result updatePatient(@RequestBody Patient patient){
        return judgmentResult(patientService.updateById(patient));
    }


    @ApiOperation("删除就诊人")
    @DeleteMapping("auth/remove/{id}")
    public Result deletePatient(@PathVariable Long id){
        return judgmentResult(patientService.removeById(id));
    }

    @ApiOperation("根据id获取就诊人信息")
    @GetMapping("inner/get/{id}")
    public Result getPatientOrder(@PathVariable Long id){
        return judgmentResult(patientService.getPatientById(id));
    }

}
