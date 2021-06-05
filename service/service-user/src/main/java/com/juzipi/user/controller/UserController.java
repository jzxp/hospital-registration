package com.juzipi.user.controller;

import com.github.pagehelper.PageInfo;
import com.juzipi.commonutil.tool.Result;
import com.juzipi.inter.model.pojo.user.UserInfo;
import com.juzipi.inter.vo.user.UserInfoSelectVo;
import com.juzipi.serviceutil.core.BaseController;
import com.juzipi.user.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author juzipi
 * @Date 2021/6/3 11:09
 * @Info
 */
@RestController
@RequestMapping("admin/user")
public class UserController extends BaseController {


    @Autowired
    private UserInfoService userInfoService;


    @ApiOperation("用户条件查询分页")
    @GetMapping("{pageNum}/{pageSize}")
    public Result getPage(@PathVariable Long pageNum, @PathVariable Long pageSize, UserInfoSelectVo userInfoSelectVo){
        PageInfo<UserInfo> userInfoPageInfo = userInfoService.queryPage(pageNum, pageSize, userInfoSelectVo);
        return pageResult(userInfoPageInfo);
    }


    @ApiOperation("锁定用户")
    @GetMapping("lock/{userId}/{status}")
    public Result lockUser(@PathVariable Long userId,@PathVariable Integer status){
        return toResult(userInfoService.lockUser(userId, status));
    }


    @ApiOperation("用户详情")
    @GetMapping("show/{userId}")
    public Result showUser(@PathVariable Long userId){
        return judgmentResult(userInfoService.showUser(userId));
    }


    @ApiOperation("认证审批")
    @GetMapping("approval/{userId}/{authStatus}")
    public Result approval(@PathVariable Long userId,@PathVariable Integer authStatus){
        return toResult(userInfoService.approval(userId, authStatus));
    }


}
