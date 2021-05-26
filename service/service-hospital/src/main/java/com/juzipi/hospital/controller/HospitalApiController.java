package com.juzipi.hospital.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.vo.hospital.HospitalSelectVo;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author juzipi
 * @Date 2021/5/16 16:01
 * @Info
 */
@RestController
@RequestMapping("api/hospital/hospital")
public class HospitalApiController extends BaseController {


    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DepartmentService departmentService;


    @ApiOperation("查询医院列表")
    @PostMapping("getPage")
    public Result getHospitalPage( @RequestBody PageBody pageBody,HospitalSelectVo hospitalSelectVo){
        return pageResult(hospitalService.queryHospitalPage(pageBody, hospitalSelectVo));
    }


    @ApiOperation("根据医院名称模糊查询")
    @GetMapping("findByHpName/{hpName}")
    public Result getHospital(@PathVariable String hpName){
        return judgmentResult(hospitalService.queryHospitalByLikeHpName(hpName));
    }


    @ApiOperation("根据医院编号获取科室")
    @GetMapping("department/{hpCode}")
    public Result getDepartment(@PathVariable String hpCode){
        return judgmentResult(departmentService.queryDepartmentList(hpCode));
    }


    @ApiOperation("医院预约挂号详情")
    @GetMapping("{hpCode}")
    public Result getReservationDetails(@PathVariable String hpCode){
        return judgmentResult(hospitalService.getReservation(hpCode));
    }


}
