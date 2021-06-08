package com.juzipi.order.controller;

import com.juzipi.commonutil.tool.Result;
import com.juzipi.order.service.OrderService;
import com.juzipi.serviceutil.core.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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





}
