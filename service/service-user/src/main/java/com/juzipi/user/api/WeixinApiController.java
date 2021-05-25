package com.juzipi.user.api;

import com.alibaba.fastjson.JSONObject;
import com.juzipi.commonutil.exception.BaseException;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.util.JwtUtils;
import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.inter.model.pojo.user.UserInfo;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.user.service.UserInfoService;
import com.juzipi.user.utils.HttpClientUtils;
import com.juzipi.user.utils.WxUserProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserInfoService userInfoService;


    //生成二维码
    @ApiOperation("获取验证码")
    @ResponseBody
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
    public String callBack(String code,String state){
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
            //解析信息获取到access_token和openid
            JSONObject jsonObject = JSONObject.parseObject(accessTokenInfo);
            String accessToken = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            //根据openid判断用户是否已存在
            UserInfo userInfo = userInfoService.checkWxUserExist(openid);
            //如果不存在添加进数据库
            if (StringUtils.isNull(userInfo)){
                //用openid和accessToken请求微信地址，获取扫描人信息
                String baseUserInfo = "https://api.weixin.qq.com/sns/userinfo"+"?access_token=%s"+"&openid=%s";
                String userInfoString = String.format(baseUserInfo, accessToken, openid);
                //获取到扫码人的信息
                String resultInfo = HttpClientUtils.get(userInfoString);
                //解析出昵称和头像
                JSONObject resultInfoJson = JSONObject.parseObject(resultInfo);
                String nickname = resultInfoJson.getString("nickname");
                String avatar = resultInfoJson.getString("headimgurl");
                //把扫描人信息添加到数据库
                userInfoService.insertUserInfo(nickname,openid);
            }

            //返回name和token
            HashMap<Object, Object> hashMap = new HashMap<>();
            hashMap.put("name",userInfo.getNickname());
            hashMap.put("openid",openid);
            //生成token
            String token = JwtUtils.createToken(userInfo.getId(), userInfo.getNickname());
            hashMap.put("token",token);
            //硬拼接路径来实现跳转
            return "redirect:" + WxUserProperties.YYT_BASEURL + "/weixin/callback?token="+token+"&openid="+openid+"&name="+ userInfo.getNickname();
        } catch (Exception e) {
            throw new BaseException(this.getClass().getName(),500,"微信api回调方法",e.getMessage());
        }
    }

}
