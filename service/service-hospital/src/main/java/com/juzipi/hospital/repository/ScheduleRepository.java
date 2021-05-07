package com.juzipi.hospital.repository;

import com.juzipi.inter.model.pojo.hospital.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:23
 * @Info
 */
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    Schedule queryScheduleByHpCodeAndHpScheduleId(String hpCode, String HpScheduleId)

}
