//package com.balobanov.messages;

import com.rabbitmq.client.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

//@Component
//@Order(1000)
//public class ConsumersInitializer {
//
//    @Autowired
//    private ConnectionFactory connectionFactory;
//
//    @PostConstruct
//    public void init() throws IOException {
//        String q = "tempQu";
//        Connection connection = connectionFactory.createConnection();
//        Channel channel = connection.createChannel(true);
//
//        AMQP.Queue.DeclareOk tempQu = channel.queueDeclare(q, false, false, false, null);
//
//        channel.basicConsume(q, new Consumer() {
//            @Override
//            public void handleConsumeOk(String s) {
//                System.out.println("sddsdsd");
//            }
//
//            @Override
//            public void handleCancelOk(String s) {
//                System.out.println("sddsdsd");
//            }
//
//            @Override
//            public void handleCancel(String s) throws IOException {
//                System.out.println("sddsdsd");
//            }
//
//            @Override
//            public void handleShutdownSignal(String s, ShutdownSignalException e) {
//                System.out.println("sddsdsd");
//            }
//
//            @Override
//            public void handleRecoverOk(String s) {
//                System.out.println("sddsdsd");
//            }
//
//            @Override
//            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
//
//                System.out.println("sddsdsd");
//            }
//        });
//    }
//}
