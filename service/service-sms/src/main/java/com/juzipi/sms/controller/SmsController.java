package com.juzipi.sms.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.tool.ResultTools;
import com.juzipi.commonutil.util.CaptchaUtils;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.serviceutil.util.RedisUtils;
import com.juzipi.sms.service.SmsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author juzipi
 * @Date 2021/5/19 18:23
 * @Info
 */
@RestController
@RequestMapping("api/sms")
public class SmsController extends BaseController {


    @Autowired
    private SmsService smsService;
    @Autowired
    private RedisUtils redisUtils;


    @ApiOperation("发送验证码")
    @GetMapping("send/{phoneNumber}")
    public Result getCode(@PathVariable String phoneNumber){
        //根据手机号（key）获取验证码
        String code = redisUtils.getCacheObject(phoneNumber).toString();
        if (StringUtils.isNotEmpty(code)){
            return ResultTools.success();
        }
        String captcha6 = CaptchaUtils.getCaptcha6();
        if (smsService.sendCode(phoneNumber,captcha6)){
            redisUtils.setCacheObject(phoneNumber,captcha6,5, TimeUnit.MINUTES);
            return ResultTools.success();
        }
        return ResultTools.fail();
    }


}
