package com.juzipi.hospital.service;

import com.github.pagehelper.PageInfo;
import com.juzipi.inter.model.mode.PageBody;
import com.juzipi.inter.model.pojo.hospital.Schedule;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:24
 * @Info
 */
public interface ScheduleService {


    Schedule insertSchedule(Map<String, Object> parameterMap);


    Page<Schedule> queryPageSchedule(Integer pageNum, Integer pageSize, String hpCode);


    Integer removeSchedule(String hpCode, String hpScheduleId);

    Map<String, Object> queryPageScheduleRule(Integer pageNum, Integer pageSize, String hpCode, String depCode);
}
