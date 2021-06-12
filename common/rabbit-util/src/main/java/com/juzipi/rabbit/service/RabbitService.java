package com.juzipi.rabbit.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author juzipi
 * @Date 2021/6/9 18:25
 * @Info
 */
@Service
public class RabbitService {


    @Resource
    private RabbitTemplate rabbitTemplate;



    public Boolean sendMessage(String exchange, String routingKey, Object message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return true;
    }
}
