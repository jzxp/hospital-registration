package com.juzipi.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzipi.inter.model.mode.LoginBody;
import com.juzipi.inter.model.user.UserInfo;
import com.juzipi.inter.vo.user.UserInfoVo;

/**
 * @Author juzipi
 * @Date 2021/5/17 16:52
 * @Info
 */
public interface UserInfoService extends IService<UserInfo> {


    UserInfoVo login(LoginBody loginBody);


}
