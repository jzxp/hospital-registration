package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.constant.MongoConstants;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.repository.ScheduleRepository;
import com.juzipi.hospital.service.ScheduleService;
import com.juzipi.inter.model.pojo.hospital.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:24
 * @Info
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    /**
     * 上传排班
     * @param parameterMap
     * @return
     */
    @Override
    public Schedule insertSchedule(Map<String, Object> parameterMap) {
        //先转为String, 再用parseObject方法 传入相应的参数转为hospitalEntity对象
        String string = JSONObject.toJSONString(parameterMap);
        Schedule schedule = JSONObject.parseObject(string, Schedule.class);
        //查询数据库得到的为空表示不存在此数据就是新增，否则就是更新
        Schedule scheduleExists = checkScheduleExists(schedule.getHpCode(), schedule.getHpScheduleId());
        if (StringUtils.isNull(scheduleExists)){
            //为空就是新增
            schedule.setCreateTime(new Date());
            //默认为1空闲
            schedule.setStatus(ConstantsMp.SCHEDULE_STATUS_VALUE_MONGO);
            schedule.setUpdateTime(schedule.getCreateTime());
            schedule.setDeleted(ConstantsMp.DELETED_VALUE);
            return scheduleRepository.save(schedule);
        }
        //不为空就是更新,删除也是在这了
        scheduleExists.setUpdateTime(new Date());
        return scheduleRepository.save(scheduleExists);
    }



    @Override
    public Page<Schedule> queryPageSchedule(Integer pageNum, Integer pageSize, String hpCode) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        Schedule schedule = new Schedule();
        schedule.setHpCode(hpCode);
        schedule.setDeleted(MongoConstants.DELETED_VALUE_FALSE);//查询未删除的
        //CONTAINING：包含，可能是类似模糊查询的吧，后面的是不区分大小写？
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true);
        return scheduleRepository.findAll(Example.of(schedule, exampleMatcher), pageable);
    }


    @Override
    public Integer removeSchedule(String hpCode, String hpScheduleId){
        Schedule schedule = scheduleRepository.queryScheduleByHpCodeAndHpScheduleId(hpCode, hpScheduleId);
        schedule.setDeleted(MongoConstants.DELETED_VALUE_TRUE);
        schedule.setUpdateTime(new Date());
        Schedule save = scheduleRepository.save(schedule);
        Schedule scheduleExists = checkScheduleExists(save.getHpCode(), save.getHpScheduleId());
        if (Objects.equals(scheduleExists.getDeleted(), MongoConstants.DELETED_VALUE_TRUE)){
            return 1;
        }
        return 0;
    }




    /**
     * 根据hpCode和depCode查询schedule
     * @param hpCode
     * @param hpScheduleId
     * @return Schedule
     */
    private Schedule checkScheduleExists(String hpCode, String hpScheduleId) {
        return scheduleRepository.queryScheduleByHpCodeAndHpScheduleId(hpCode, hpScheduleId);
    }


}
