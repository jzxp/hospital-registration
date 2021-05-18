package com.juzipi.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzipi.inter.model.mode.LoginBody;
import com.juzipi.inter.model.pojo.user.UserInfo;

import java.util.HashMap;

/**
 * @Author juzipi
 * @Date 2021/5/17 16:52
 * @Info
 */
public interface UserInfoService extends IService<UserInfo> {


    HashMap<String, Object> login(LoginBody loginBody);


}
