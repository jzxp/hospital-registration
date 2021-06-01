package com.juzipi.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzipi.inter.model.mode.LoginBody;
import com.juzipi.inter.model.pojo.user.UserInfo;
import com.juzipi.inter.vo.user.UserAuthVo;

import java.util.HashMap;

/**
 * @Author juzipi
 * @Date 2021/5/17 16:52
 * @Info
 */
public interface UserInfoService extends IService<UserInfo> {


    HashMap<String, Object> login(LoginBody loginBody);


    Integer insertUserInfo(String nickname, String openid);

    UserInfo checkWxUserExist(String openid);

    Integer auth(Long userId, UserAuthVo userAuthVo);
}
