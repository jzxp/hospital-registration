package com.juzipi.hospital.service;

import com.juzipi.inter.model.pojo.hospital.Schedule;

import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:24
 * @Info
 */
public interface ScheduleService {


    Schedule insertSchedule(Map<String, Object> parameterMap);

}
