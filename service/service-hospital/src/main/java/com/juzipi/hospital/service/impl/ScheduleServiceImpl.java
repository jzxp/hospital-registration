package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.repository.ScheduleRepository;
import com.juzipi.hospital.service.ScheduleService;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import com.juzipi.inter.model.pojo.hospital.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:24
 * @Info
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public Schedule insertSchedule(Map<String, Object> parameterMap) {
        //先转为String, 再用parseObject方法 传入相应的参数转为hospitalEntity对象
        String string = JSONObject.toJSONString(parameterMap);
        Schedule schedule = JSONObject.parseObject(string, Schedule.class);

        //查询数据库得到的为空表示不存在此数据就是新增，否则就是更新
        //此处有问题，更新操作依然是新增
        Schedule scheduleExists = checkScheduleExists(schedule.getHpCode());
        if (StringUtils.isNull(scheduleExists)){
            //为空就是新增
            schedule.setCreateTime(new Date());
            schedule.setStatus(ConstantsMp.STATUS_VALUE_MONGO);
            schedule.setUpdateTime(schedule.getCreateTime());
            schedule.setDeleted(ConstantsMp.DELETED_VALUE);
            return scheduleRepository.save(schedule);
        }
        //不为空就是更新
        scheduleExists.setUpdateTime(new Date());
        //重复添加会报错，这个新增更新的操作也不透明，看不到它干了什么
        return scheduleRepository.save(scheduleExists);
    }



    private Schedule checkScheduleExists(String hpCode) {

        scheduleRepository.query
    }


}
