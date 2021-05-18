package com.juzipi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.util.JwtUtils;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.inter.model.mode.LoginBody;
import com.juzipi.inter.model.pojo.user.UserInfo;
import com.juzipi.user.mapper.UserInfoMapper;
import com.juzipi.user.service.UserInfoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author juzipi
 * @Date 2021/5/17 16:52
 * @Info
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


    @Override
    public HashMap<String, Object> login(LoginBody loginBody) {
        //判断手机号验证码
        String phoneNumber = loginBody.getPhoneNumber();
        String code = loginBody.getCode();
        if (StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(code)){
            throw new BaseException(this.getClass().getName(),403,loginBody,"手机号或验证码为空");
        }
        //判断是否是第一次登录
        UserInfo userInfo = baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getPhoneNumber, phoneNumber));
        if (StringUtils.isNull(userInfo)){
            userInfo = new UserInfo();
            userInfo.setPhoneNumber(phoneNumber);
            Integer register = baseMapper.insert(userInfo);
            if (register == 0){
                throw new BaseException(500,"注册失败");
            }
        }

        //此处有问题
        if (userInfo.getStatus() == 0){
            throw new BaseException(this.getClass().getName(),403,loginBody,"账号已被禁用");
        }
        //生成token
        String token = JwtUtils.createToken(userInfo.getId(), userInfo.getName());
        HashMap<String, Object> hashMap = new HashMap<>();
        String nickname = userInfo.getNickname();
        if (StringUtils.isEmpty(nickname)){
            nickname = userInfo.getPhoneNumber();
        }
        hashMap.put("name",nickname);
        hashMap.put("token",token);

        //返回部分信息
        return hashMap;
    }
}
