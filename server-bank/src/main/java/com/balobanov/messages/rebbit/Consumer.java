package com.balobanov.messages.rebbit;

import com.balobanov.models.Bank;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer{

    @RabbitListener(queues="${banks.rabbitmq.queue}", containerFactory="jsaFactory")
    public void recievedMessage(Bank bank) {
        System.out.println("Recieved Message: " + bank);
    }
}