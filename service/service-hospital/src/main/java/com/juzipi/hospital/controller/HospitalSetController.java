package com.juzipi.hospital.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.juzipi.commonutil.tools.PageTool;
import com.juzipi.commonutil.tools.Result;
import com.juzipi.hospital.service.HospitalSetService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.model.pojo.HospitalSet;
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
@RequestMapping("hospitalSet")
public class HospitalSetController extends BaseController {

    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation(value = "获取所有医院")
    @GetMapping("list")
    public Result selectHospitalList(){
        return JudgmentResult(hospitalSetService.list());
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping("del/{id}")
    public Result deleteById(@PathVariable Long id){
        return JudgmentResult(hospitalSetService.removeById(id));
    }


    @ApiOperation(value = "新增")
    @PostMapping("insert")
    public Result insertHospital(@RequestBody HospitalSet hospitalSet){
        return BooleanResult(hospitalSetService.save(hospitalSet));
    }


    @ApiOperation(value = "更新")
    @PutMapping("update")
    public Result updateHospital(@RequestBody HospitalSet hospitalSet){
        return BooleanResult(hospitalSetService.updateById(hospitalSet));
    }


    @ApiOperation(value = "查询分页结果集")
    @PostMapping("page")
    public Result selectPage(@RequestBody PageBody pageBody){
        Page<HospitalSet> page = hospitalSetService.page(new Page<>(pageBody.getPageNum(), pageBody.getPageSize()));
        return JudgmentResult(PageTool.getPageResult(page));
    }

}
