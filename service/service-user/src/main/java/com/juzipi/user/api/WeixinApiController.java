package com.juzipi.user.api;

import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.user.utils.UserProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Author juzipi
 * @Date 2021/5/20 19:30
 * @Info
 */
@Controller
@RequestMapping("api/ucenter/wx")
public class WeixinApiController extends BaseController {


    //生成二维码
    @GetMapping("getLoginParam")
    public Result getQrConnect(){
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("appid", UserProperties.WX_OPEN_APP_ID);
            hashMap.put("scope", "snsapi_login");
            String wxOpenRedirectUrl = UserProperties.WX_OPEN_REDIRECT_URL;
            wxOpenRedirectUrl = URLEncoder.encode(wxOpenRedirectUrl, "utf-8");
            hashMap.put("redirect_uri",wxOpenRedirectUrl);
            hashMap.put("state", System.currentTimeMillis()+"");
            return judgmentResult(hashMap);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(500,"生成二维码失败");
        }

    }


    //回调的方法



}
