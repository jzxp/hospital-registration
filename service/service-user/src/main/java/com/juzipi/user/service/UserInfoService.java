package com.juzipi.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.juzipi.inter.model.mode.LoginBody;
import com.juzipi.inter.model.pojo.user.UserInfo;
import com.juzipi.inter.vo.user.UserAuthVo;
import com.juzipi.inter.vo.user.UserInfoSelectVo;

import java.util.HashMap;
import java.util.Map;

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

    PageInfo<UserInfo> queryPage(Long pageNum, Long pageSize, UserInfoSelectVo userInfoSelectVo);

    Integer lockUser(Long userId, Integer status);

    Map<String,Object> showUser(Long userId);

    Integer approval(Long userId, Integer authStatus);
}
