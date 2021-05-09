package com.juzipi.hospital.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.vo.hospital.HospitalSelectVo;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author juzipi
 * @Date 2021/5/8 17:51
 * @Info
 */
@Api(tags = "医院管理")
@RestController
@RequestMapping("admin/hospital")
@CrossOrigin
public class HospitalController extends BaseController {


    @Autowired
    private HospitalService hospitalService;



    /*
    理一下思路：他的fegin远程调用作用是，hospital表里一些字段只有它对应的编码值
    它编码值对应的具体的值在data_dict表里（数据字典），需要用到dict的方法，
    但它们不在同一个模块，所以就用上了远程调用来查询
     */
    @ApiOperation("查询医院分页结果集")
    @PostMapping("list/page")
    public Result getPageListHospital(@RequestBody PageBody pageBody, HospitalSelectVo hospitalSelectVo){
        return pageResult(hospitalService.queryHospitalPage(pageBody, hospitalSelectVo));
    }



}
