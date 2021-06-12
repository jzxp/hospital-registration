package com.juzipi.hospital.receiver;

import com.juzipi.commonutil.util.StringUtils;
import com.juzipi.hospital.service.ScheduleService;
import com.juzipi.inter.model.pojo.hospital.Schedule;
import com.juzipi.inter.vo.order.OrderMqVo;
import com.juzipi.inter.vo.sms.SmsVo;
import com.juzipi.rabbit.constant.MqConstants;
import com.juzipi.rabbit.service.RabbitService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author juzipi
 * @Date 2021/6/10 20:30
 * @Info
 */
@Component
public class HospitalReceiver {


    @Resource
    private ScheduleService scheduleService;

    @Resource
    private RabbitService rabbitService;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConstants.QUEUE_ORDER, durable = "true"),
            exchange = @Exchange(value = MqConstants.EXCHANGE_DIRECT_ORDER),
            key = {MqConstants.ROUTING_ORDER}
    ))
    public void receiver(OrderMqVo orderMqVo, Message message, Channel channel){
        Schedule schedule = scheduleService.getScheduleById(orderMqVo.getScheduleId());
        schedule.setReservedNumber(orderMqVo.getReservedNumber());
        schedule.setAvailableNumber(orderMqVo.getAvailableNumber());
        if (scheduleService.updateSchedule(schedule)) {
            SmsVo smsVo = orderMqVo.getSmsVo();
            if (StringUtils.isNotNull(smsVo)){
                rabbitService.sendMessage(MqConstants.QUEUE_MSM_ITEM, MqConstants.ROUTING_MSM_ITEM, smsVo);
            }
        }


    }



}
