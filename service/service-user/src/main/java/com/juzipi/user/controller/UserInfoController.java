package com.juzipi.user.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.inter.model.mode.LoginBody;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.serviceutil.util.RedisUtils;
import com.juzipi.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author juzipi
 * @Date 2021/5/17 16:51
 * @Info
 */
@RestController
@RequestMapping("api/user")
public class UserInfoController extends BaseController {


    @Autowired
    private UserInfoService userInfoService;



    @PostMapping("login")
    public Result login(@RequestBody LoginBody loginBody){
        return judgmentResult(userInfoService.login(loginBody));
    }



}
