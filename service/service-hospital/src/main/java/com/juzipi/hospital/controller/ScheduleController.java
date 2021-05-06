package com.juzipi.hospital.controller;

import com.juzipi.hospital.service.ScheduleService;
import com.juzipi.serviceutil.core.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:25
 * @Info
 */
@RestController
@RequestMapping
public class ScheduleController extends BaseController {


    @Autowired
    private ScheduleService scheduleService;



}
