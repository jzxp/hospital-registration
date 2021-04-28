package com.juzipi.hospital.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.hospital.service.HospitalSetService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.model.pojo.HospitalSet;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/4/27 14:35
 * @Info
 */
@Api(tags = "医院管理")
@RestController
@RequestMapping("hospitalSet")
public class HospitalSetController extends BaseController {

    @Autowired
    private HospitalSetService hospitalSetService;



    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("list")
    public Result selectHospitalSetList(){
        return judgmentResult(hospitalSetService.list());
    }


    @ApiOperation(value = "根据id查询医院设置")
    @GetMapping("get/{id}")
    public Result selectById(@PathVariable Long id){
        return judgmentResult(hospitalSetService.getById(id));
    }


    @ApiOperation(value = "根据id删除医院设置")
    @DeleteMapping("del/{id}")
    public Result deleteById(@PathVariable Long id){
        return judgmentResult(hospitalSetService.removeById(id));
    }



    @ApiOperation(value = "新增医院设置")
    @PostMapping("insert")
    public Result insertHospitalSet(@RequestBody HospitalSet hospitalSet){
        return toResult(hospitalSetService.addHospitalSet(hospitalSet));
    }



    @ApiOperation(value = "更新医院设置")
    @PutMapping("update")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet){
        return toResult(hospitalSetService.modifyHospitalSet(hospitalSet));
    }


    @ApiOperation(value = "查询分页医院设置")
    @PostMapping("page")
    public Result selectPage(@RequestBody PageBody pageBody){
        return pageResult(hospitalSetService.queryHospitalPage(pageBody));
    }


    public Result deleteByIds(@RequestBody List<Long> idList){

    }

}
