package com.juzipi.hospital.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.serviceutil.core.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author juzipi
 * @Date 2021/5/8 17:51
 * @Info
 */
@RestController
@RequestMapping("admin/hospital")
@CrossOrigin
public class HospitalController extends BaseController {


    @Autowired
    private HospitalService hospitalService;


    @PostMapping("list/page")
    public Result getPageListHospital(@RequestBody PageBody pageBody){
        return judgmentResult(hospitalService.queryHospitalPage(pageBody));
    }


}
