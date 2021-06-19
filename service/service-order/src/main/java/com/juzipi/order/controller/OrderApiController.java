package com.juzipi.order.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.commonutil.util.AuthContextHolder;
import com.juzipi.inter.vo.order.OrderSelectVo;
import com.juzipi.order.service.OrderService;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author juzipi
 * @Date 2021/6/6 21:54
 * @Info
 */
@RestController
@RequestMapping("api/order/orderInfo")
public class OrderApiController extends BaseController {


    @Autowired
    private OrderService orderService;

    @ApiOperation("保存订单返回订单id")
    @GetMapping("auth/submitOrder/{scheduleId}/{patientId}")
    public Result saveOrder(@PathVariable String scheduleId,@PathVariable Long patientId){
        return judgmentResult(orderService.saveOrder(scheduleId,patientId));
    }


    @GetMapping("auth/getOrder/{orderId}")
    public Result getOrder(@PathVariable String orderId){
        return judgmentResult(orderService.getOrder(orderId));
    }


    @PostMapping("auth/{pageNum}/{pageSize}")
    public Result getOrderPage(
            @PathVariable Long pageNum,
            @PathVariable Long pageSize,
            OrderSelectVo orderSelectVo,
            HttpServletRequest request
    ){
        orderSelectVo.setUserId(AuthContextHolder.getUserId(request));
        orderService.getPage(pageNum,pageSize,orderSelectVo);
    }


    @ApiOperation(value = "获取订单状态")
    @GetMapping("auth/getStatusList")
    public Result getStatusList(){

    }



}
