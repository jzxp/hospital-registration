package com.juzipi.hospital.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.juzipi.commonutil.tools.PageTool;
import com.juzipi.commonutil.tools.Result;
import com.juzipi.commonutil.tools.ResultTool;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.model.pojo.Hospital;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author juzipi
 * @Date 2021/4/27 14:35
 * @Info
 */
@Api(tags = "医院管理")
@RestController
@RequestMapping("hospital")
public class HospitalController extends BaseController {

    @Autowired
    private HospitalService hospitalService;

    @ApiOperation(value = "获取所有医院")
    @GetMapping("list")
    public Result selectHospitalList(){
        return Judgment(hospitalService.list());
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("del/{id}")
    public Result deleteById(@PathVariable Long id){
        return Judgment(hospitalService.removeById(id));
    }


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insertHospital(@RequestBody Hospital hospital){
        return Judgment(hospitalService.save(hospital));
    }


    @ApiOperation(value = "更新")
    @PutMapping("update")
    public Result updateHospital(@RequestBody Hospital hospital){
        return Judgment(hospitalService.updateById(hospital));
    }

    public Result selectPage(@RequestBody PageBody pageBody){
        hospitalService.page(PageTool.getPage())
    }

}
