package com.juzipi.hospital.repository;

import com.juzipi.inter.model.pojo.hospital.Schedule;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:23
 * @Info
 */
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    Schedule queryScheduleByHpCodeAndHpScheduleId(String hpCode, String HpScheduleId);

    List<Schedule> queryByHpCodeAndDepCode(String hpCode,String depCode);

    List<Schedule> queryByHpCodeAndDepCodeAndWorkDate(String hpCode,String depCode, DateTime workDate);

    Schedule queryScheduleById(String scheduleId);

}
