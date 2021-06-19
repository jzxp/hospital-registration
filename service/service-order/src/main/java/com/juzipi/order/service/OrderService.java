package com.juzipi.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.juzipi.inter.model.pojo.order.OrderInfo;
import com.juzipi.inter.vo.order.OrderSelectVo;

/**
 * @Author juzipi
 * @Date 2021/6/6 21:54
 * @Info
 */
public interface OrderService extends IService<OrderInfo> {


    Long saveOrder(String scheduleId, Long patientId);


    OrderInfo getOrder(String orderId);

    void getPage(Long pageNum, Long pageSize, OrderSelectVo orderSelectVo);

}
