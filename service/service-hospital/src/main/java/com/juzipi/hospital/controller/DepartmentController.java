package com.juzipi.hospital.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author juzipi
 * @Date 2021/5/5 19:51
 * @Info
 */
@Api(tags = "科室信息")
@RestController
@RequestMapping("admin/department")
public class DepartmentController extends BaseController {


    @Autowired
    private DepartmentService departmentService;


    @ApiOperation("查询医院所有科室列表")
    @GetMapping("getDeptList/{hpCode}")
    public Result getList(@PathVariable String hpCode){
        return judgmentResult(departmentService.queryDepartmentList(hpCode));
    }


}
