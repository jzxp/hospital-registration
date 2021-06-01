package com.juzipi.user.api;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.util.AuthContextHolder;
import com.juzipi.inter.model.mode.LoginBody;
import com.juzipi.inter.vo.user.UserAuthVo;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author juzipi
 * @Date 2021/5/17 16:51
 * @Info
 */
@Api(tags = "用户")
@RestController
@RequestMapping("api/user")
public class UserInfoApiController extends BaseController {


    @Autowired
    private UserInfoService userInfoService;



    @PostMapping("login")
    public Result login(@RequestBody LoginBody loginBody){
        return judgmentResult(userInfoService.login(loginBody));
    }


    @ApiOperation("用户认证")
    @PostMapping("auth/userAuth")
    public Result authUser(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request){
        return toResult(userInfoService.auth(AuthContextHolder.getUserId(request), userAuthVo));
    }


    @ApiOperation("获取用户信息")
    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request){
        return judgmentResult(userInfoService.getById(AuthContextHolder.getUserId(request)));
    }

}
