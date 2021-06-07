package com.juzipi.order.service.impl;

import com.alibaba.excel.event.Order;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.juzipi.inter.model.pojo.order.OrderInfo;
import com.juzipi.order.mapper.OrderMapper;
import com.juzipi.order.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Author juzipi
 * @Date 2021/6/6 21:57
 * @Info
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {


    @Override
    public Long saveOrder(String scheduleId, Long patientId) {
        return null;
    }


}
