package com.juzipi.sms.receiver;

import com.juzipi.inter.vo.sms.SmsVo;
import com.juzipi.rabbit.constant.MqConstants;
import com.juzipi.sms.service.SmsService;
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
 * @Date 2021/6/10 20:13
 * @Info
 */
@Component
public class SmsReceiver {


    @Resource
    private SmsService smsService;


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = MqConstants.QUEUE_MSM_ITEM, durable = "true"),
    exchange = @Exchange(value = MqConstants.EXCHANGE_DIRECT_MSM),
            key = {MqConstants.ROUTING_MSM_ITEM}
    ))
    public void send(SmsVo smsVo, Message message, Channel channel){
        smsService.sendMQ(smsVo);
    }

}
