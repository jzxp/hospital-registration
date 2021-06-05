package com.juzipi.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.juzipi.commonutil.constant.UserConstants;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.util.JwtUtils;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.commonutil.util.conversion.UserInfoAuthStatusConversion;
import com.juzipi.inter.model.mode.LoginBody;
import com.juzipi.inter.model.pojo.user.Patient;
import com.juzipi.inter.model.pojo.user.UserInfo;
import com.juzipi.inter.vo.user.UserAuthVo;
import com.juzipi.inter.vo.user.UserInfoSelectVo;
import com.juzipi.serviceutil.util.RedisUtils;
import com.juzipi.user.mapper.UserInfoMapper;
import com.juzipi.user.service.PatientService;
import com.juzipi.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author juzipi
 * @Date 2021/5/17 16:52
 * @Info
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private PatientService patientService;

    @Override
    public HashMap<String, Object> login(LoginBody loginBody) {
        //判断手机号验证码
        String phoneNumber = loginBody.getPhoneNumber();
        String code = loginBody.getCode();
        if (StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(code)){
            throw new BaseException(this.getClass().getName(),403,loginBody,"手机号或验证码为空");
        }
        String captcha = redisUtils.getCacheObject(loginBody.getPhoneNumber()).toString();
        if (!Objects.equals(loginBody.getCode(), captcha)){
            throw new BaseException("验证码错误");
        }

        //绑定手机号
        UserInfo userInfo = null;
        if (StringUtils.isNotEmpty(loginBody.getOpenid())){
            //如果用户不为空绑定手机号
            userInfo = this.checkWxUserExist(loginBody.getOpenid());
            if (StringUtils.isNotNull(userInfo)){
                userInfo.setPhoneNumber(loginBody.getPhoneNumber());
                baseMapper.updateById(userInfo);
            }
        }

        //判断是否是第一次登录,为空进行正常登录
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

    @Override
    public Integer insertUserInfo(String nickname, String openid) {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(nickname);
        userInfo.setOpenid(openid);
        return baseMapper.insert(userInfo);
    }


    @Override
    public UserInfo checkWxUserExist(String openid) {
        return baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getOpenid, openid));
    }



    @Override
    public Integer auth(Long userId, UserAuthVo userAuthVo) {
        UserInfo userInfo = baseMapper.selectById(userId);
        userInfo.setName(userAuthVo.getName());
        userInfo.setCertificatesNo(userAuthVo.getCertificatesNo());
        userInfo.setCertificatesType(userAuthVo.getCertificatesType());
        userInfo.setCertificatesUrl(userAuthVo.getCertificatesUrl());
        userInfo.setAuthStatus(UserConstants.USER_STATUS_ON);
        return baseMapper.updateById(userInfo);
    }


    @Override
    public PageInfo<UserInfo> queryPage(Long pageNum, Long pageSize, UserInfoSelectVo userInfoSelectVo) {
        //用户姓名
        String name = userInfoSelectVo.getKeyword();
        //用户状态
        Integer status = userInfoSelectVo.getStatus();
        //认证状态
        Integer authStatus = userInfoSelectVo.getAuthStatus();
        //开始时间
        String createTimeBegin = userInfoSelectVo.getCreateTimeBegin();
        //结束时间
        String createTimeEnd = userInfoSelectVo.getCreateTimeEnd();
        PageHelper.startPage(pageNum.intValue(),pageSize.intValue());
        List<UserInfo> userInfos = baseMapper.selectList(new QueryWrapper<UserInfo>().lambda()
                .like(UserInfo::getName, name)
                .eq(UserInfo::getStatus, status)
                .eq(UserInfo::getAuthStatus, authStatus)
                .ge(UserInfo::getCreateTime, createTimeBegin)
                .le(UserInfo::getCreateTime, createTimeEnd)
        );
        PageInfo<UserInfo> userInfoPageInfo = new PageInfo<>();
        userInfoPageInfo.getList().forEach(this::setUserInfo);
        return userInfoPageInfo;
    }


    @Override
    public Integer lockUser(Long userId, Integer status) {
        if (status == 0 || status == 1){
            UserInfo userInfo = baseMapper.selectById(userId);
            userInfo.setStatus(status);
            return baseMapper.updateById(userInfo);
        }
        return 0;
    }


    @Override
    public Map<String, Object> showUser(Long userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        //根据userId查询用户信息
        UserInfo userInfo = this.setUserInfo(baseMapper.selectById(userId));
        hashMap.put("userInfo",userInfo);
        //根据userId查询就诊人信息
        Patient patients = patientService.getPatientById(userId);
        hashMap.put("patients",patients);
        return hashMap;
    }



    @Override
    public Integer approval(Long userId, Integer authStatus) {
        if (authStatus == 2 || authStatus == -1){
            UserInfo userInfo = baseMapper.selectById(userId);
            userInfo.setAuthStatus(authStatus);
            return baseMapper.updateById(userInfo);
        }
        return 0;
    }


    private UserInfo setUserInfo(UserInfo userInfo){
        //认证状态转换
        userInfo.getParam().put("authStatusString", UserInfoAuthStatusConversion.stateTransition(userInfo.getStatus()));
        //用户状态
        String statusString = userInfo.getStatus() == 0 ? "锁定" : "正常";
        userInfo.getParam().put("statusString",statusString);
        return userInfo;
    }

}
