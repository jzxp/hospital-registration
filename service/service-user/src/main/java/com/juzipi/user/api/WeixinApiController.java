package com.juzipi.user.api;

import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.user.utils.HttpClientUtils;
import com.juzipi.user.utils.WxUserProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("微信api")
@Controller
@RequestMapping("api/ucenter/wx")
public class WeixinApiController extends BaseController {


    //生成二维码
    @ApiOperation("获取验证码")
    @GetMapping("getLoginParam")
    public Result getQrConnect(){
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("appid", WxUserProperties.WX_OPEN_APP_ID);
            hashMap.put("scope", "snsapi_login");
            String wxOpenRedirectUrl = WxUserProperties.WX_OPEN_REDIRECT_URL;
            wxOpenRedirectUrl = URLEncoder.encode(wxOpenRedirectUrl, "utf-8");
            hashMap.put("redirect_uri",wxOpenRedirectUrl);
            hashMap.put("state", System.currentTimeMillis()+"");
            return judgmentResult(hashMap);
        } catch (UnsupportedEncodingException e) {
            throw new BaseException(500,"生成二维码失败");
        }

    }


    //回调的方法
    @GetMapping("callback")
    public Result callBack(String code,String state){
        //获取临时票据
        //其实就是一个拼接的请求路径， %s 是占位符吧
        StringBuilder stringBuilder = new StringBuilder()
                .append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=%s")
                .append("&secret=%s")
                .append("&code=%s")
                .append("&grant_type=authorization_code");
        String accessTokenUrl = String.format(stringBuilder.toString(), WxUserProperties.WX_OPEN_APP_ID, WxUserProperties.WX_OPEN_APP_SECRET, code);
        //使用httpClient请求这个地址
        try {
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            return judgmentResult(accessTokenInfo);
        } catch (Exception e) {
            throw new BaseException(this.getClass().getName(),500,"微信api回调方法",e.getMessage());
        }
    }

}
