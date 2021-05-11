package com.juzipi.hospital.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.hospital.service.ScheduleService;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:25
 * @Info
 */
@Api(tags = "日程管理")
@RestController
@RequestMapping("admin/schedule")
@CrossOrigin
public class ScheduleController extends BaseController {


    @Autowired
    private ScheduleService scheduleService;


    @ApiOperation("查询排班规则数据")
    @PostMapping("getScheduleRule/{pageNum}/{pageSize}/{hpCode}/{depCode}")
    public Result getScheduleRule(
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize,
            @PathVariable String hpCode,
            @PathVariable String depCode
                                  ){
        Map<String, Object> map = scheduleService.queryPageScheduleRule(pageNum, pageSize, hpCode, depCode);
        return judgmentResult(map);
    }

}
