package com.juzipi.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.juzipi.sms.service.SmsService;
import com.juzipi.sms.utils.SmsProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @Author juzipi
 * @Date 2021/5/19 18:24
 * @Info
 */
@Service
public class SmsServiceImpl implements SmsService {


    @Override
    public Boolean sendCode(String phoneNumber, String captcha6) {
        DefaultProfile profile = DefaultProfile.
                getProfile(SmsProperties.REGION_ID,
                        SmsProperties.ACCESS_KEY_ID,
                        SmsProperties.SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //手机号
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        //签名名称
        request.putQueryParameter("SignName", "一约通");
        //模板code
        request.putQueryParameter("TemplateCode", "SMS_180051135");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code",captcha6);

        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(hashMap));

        //调用方法进行短信发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }


}
