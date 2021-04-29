package com.juzipi.hospital.controller;

import com.juzipi.commonutil.constant.BaseConstant;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.tool.ResultCode;
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
@RequestMapping("admin/hospitalSet")
public class HospitalSetController extends BaseController {

    @Autowired
    private HospitalSetService hospitalSetService;



    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("list")
    public Result selectHospitalSetList(){
        int i = 1/0;
        return judgmentResult(hospitalSetService.list());
    }


    @ApiOperation(value = "根据id查询医院设置")
    @GetMapping("get/{id}")
    public Result selectById(@PathVariable Long id){
        try {
            int i = 1/0;
        } catch (Exception e) {
            throw new BaseException(this.getClass().getName(), BaseConstant.Error, id,e.getMessage());
        }
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


    @ApiOperation(value = "批量删除")
    @DeleteMapping("del/batch")
    public Result deleteByIds(@RequestBody List<Long> idList){
        return booleanResult(hospitalSetService.removeByIds(idList));
    }


    @ApiOperation(value = "医院设置锁定和解锁")
    @PutMapping("lock/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,@PathVariable Integer status){
        return toResult(hospitalSetService.lockHospitalSet(id,status));
    }


    @ApiOperation(value = "发送签名密钥")
    @PutMapping("sendKey/{id}")
    public Result sendKey(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return judgmentResult(hospitalSet.getHpCode(),hospitalSet.getSignKey());
    }

}
