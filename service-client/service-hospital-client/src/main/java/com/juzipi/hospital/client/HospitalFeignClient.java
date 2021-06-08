package com.juzipi.hospital.client;

import com.juzipi.commonutil.tool.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author juzipi
 * @Date 2021/6/8 10:58
 * @Info
 */
@FeignClient("service-hospital")
public interface HospitalFeignClient {


    /**
     * 根据排班id获取预约下单数据
     * @param scheduleId
     * @return
     */
    @GetMapping("api/hospital/hospital/inner/getScheduleOrderVo/{scheduleId}")
    public Result getScheduleOrderVo(@PathVariable("scheduleId") String scheduleId);


    /**
     * 根据医院编号获取签名信息
     * @param hpCode
     * @return
     */
    @GetMapping("api/hospital/hospital/inner/getSignInfoVo/{hpCode}")
    public Result getSognInfoVo(@PathVariable("hpCode") String hpCode);

}
