package com.juzipi.hospital.service;

import com.juzipi.inter.model.pojo.hospital.Schedule;
import com.juzipi.inter.vo.hospital.ScheduleOrderVo;
import org.springframework.data.domain.Page;

import java.util.List;
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


    List<Schedule> getScheduleDetails(String hpCode, String depCode, String workDate);


    Map<String, Object> getBookingSchedulePage(Integer pageNum, Integer pageSize, String hpCode, String depCode);


    Schedule getScheduleById(String scheduleId);


    ScheduleOrderVo getScheduleOrderVo(String scheduleId);


    Boolean updateSchedule(Schedule schedule);

}
