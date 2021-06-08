package com.juzipi.user.client;

import com.juzipi.commonutil.tool.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author juzipi
 * @Date 2021/6/8 10:14
 * @Info
 */
@FeignClient("service-user")
public interface PatientFeginClient {


    @GetMapping("api/user/patient/inner/get/{id}")
    public Result getPatientOrder(@PathVariable("id") Long id);

}
