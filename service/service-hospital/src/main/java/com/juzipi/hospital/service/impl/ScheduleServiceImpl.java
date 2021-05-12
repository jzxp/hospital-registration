package com.juzipi.hospital.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juzipi.commonutil.constant.ConstantsMp;
import com.juzipi.commonutil.constant.MongoConstants;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.repository.ScheduleRepository;
import com.juzipi.hospital.service.DepartmentService;
import com.juzipi.hospital.service.HospitalService;
import com.juzipi.hospital.service.ScheduleService;
import com.juzipi.inter.model.pojo.hospital.Hospital;
import com.juzipi.inter.model.pojo.hospital.Schedule;
import com.juzipi.inter.vo.hospital.BookingScheduleRuleVo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author juzipi
 * @Date 2021/5/6 14:24
 * @Info
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private DepartmentService departmentService;


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



    @Override
    public Map<String, Object> queryPageScheduleRule(Integer pageNum, Integer pageSize, String hpCode, String depCode) {
        //不会写，这里逻辑太乱，赶进度，等有时间再琢磨琢磨
        //查询条件
        Criteria criteria = Criteria.where("hpCode").is(hpCode).and("depCode").is(depCode);
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),//匹配条件
                Aggregation.group("workDate")//根据XX分组
                        .first("workDate").as("workDate")
                        .count().as("docCount")
                        .sum("reservedNumber").as("reservedNumber")
                        .sum("availableNumber").as("availableNumber"),
                Aggregation.sort(Sort.Direction.DESC, "workDate"),//排序
                Aggregation.skip((pageNum - 1) * pageSize),//分页效果
                Aggregation.limit(pageSize)
        );
        AggregationResults<BookingScheduleRuleVo> aggregate = mongoTemplate.aggregate(aggregation, Schedule.class, BookingScheduleRuleVo.class);
        //执行方法
        List<BookingScheduleRuleVo> bookingScheduleRuleVos = aggregate.getMappedResults();
        //分组查询总记录数
        Aggregation totalAgg = Aggregation.newAggregation(Aggregation.match(criteria), Aggregation.group("workDate"));
        AggregationResults<BookingScheduleRuleVo> aggregationResults = mongoTemplate.aggregate(totalAgg, Schedule.class, BookingScheduleRuleVo.class);

        //获取条数
        int total = aggregationResults.getMappedResults().size();
        //根据日期转换星期
        bookingScheduleRuleVos.forEach(bookingScheduleRuleVo -> {
            Date workDate = bookingScheduleRuleVo.getWorkDate();
            String week = this.dateToWeek(workDate.toString());
            bookingScheduleRuleVo.setDayOfWeek(week);
        });

        //设置最终数据返回
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("bookingScheduleRuleList",bookingScheduleRuleVos);
        resultMap.put("total",total);
        //获取医院名称
        Hospital hospital = hospitalService.getHospitalByhpCode(hpCode);
        if (StringUtils.isNull(hospital)){
            return null;
        }
        resultMap.put("hpName", hospital.getHpName());
        return resultMap;
    }

    @Override
    public List<Schedule> getScheduleDetails(String hpCode, String depCode, String workDate) {
        //查出List<Schedule>
        List<Schedule> schedules = scheduleRepository.queryByHpCodeAndDepCodeAndWorkDate(hpCode, depCode, new DateTime(workDate));
        //设置其他值
        schedules.forEach(this::setScheduleInfo);
        return schedules;
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


    /**
     * 根据日期获取星期
     * @param workDate
     * @return
     */
    private String dateToWeek(String workDate) {
        /*
        有点绕啦，因为传过来的数据是 Thu Jul 22 23:58:32 CST 2013 这种格式的，
        需要把它转成 2015-13-16 这种格式的，
         */
        DateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.US);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        /*
        这里是这样的，SimpleDateFormat转换后得到的确实是 2015-13-16 这种的，但它是String类型
        下面的calendar需要的是Date类型的，如果再用这个对象的方法去转成Date类型，它会转成原来的 Thu Jul 22 23:58:32 CST 2013 这种类型
        所以就需要再new一个新的SimpleDateFormat
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate;
        String formatString;
        Date parse;
        try {
            parseDate = simpleDateFormat.parse(workDate);
            formatString = dateFormat.format(parseDate);
            parse = format.parse(formatString);
        } catch (ParseException e) {
            throw new BaseException(this.getClass().getName(),500,null,"日期转换报错");
        }
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        //一周的第几天
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


    /**
     * 设置排班详情里的 医院名称，科室名称，日期对应星期
     * @param schedule
     * @return
     */
    private Map<String, Object> setScheduleInfo(Schedule schedule){
        Map<String, Object> scheduleParam = schedule.getParam();
        scheduleParam.put("hpName",hospitalService.getHospitalByhpCode(schedule.getHpCode()).getHpName());
        scheduleParam.put("depName",departmentService.getDepartment(schedule.getDepCode()).getDepName());
        scheduleParam.put("dayOfWeek",this.dateToWeek(schedule.getWorkDate().toString()));
        return scheduleParam;
    }

}
