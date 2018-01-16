package com.balobanov.controllers;

import com.balobanov.messages.rabbit.Producer;
import com.balobanov.models.Bank;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(value = "/activemq")
public class ActiveMqController {

    private Producer producer;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void sendMessage(){
        Bank bank = new Bank();
        bank.setName(RandomStringUtils.random(10));
        bank.setId(new Random().nextLong());
        producer.produce(bank);
    }

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
