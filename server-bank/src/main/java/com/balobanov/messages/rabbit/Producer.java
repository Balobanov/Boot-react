package com.balobanov.messages.rabbit;

import com.balobanov.models.Bank;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    @Qualifier(value = "amqpTemplate")
    private AmqpTemplate amqpTemplate;

    @Value("${banks.rabbitmq.exchange}")
    private String exchange;

    @Value("${banks.rabbitmq.routingkey}")
    private String routingkey;

    public void produce(Bank bank){
        amqpTemplate.convertAndSend(exchange, routingkey, bank);
        System.out.println("Send msg = " + bank);
    }
}
