package com.balobanov.config.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
@ImportResource("classpath:integration-ftp.xml")
public class Ftp {

    @Bean(name = "ftpClientFactory")
    public DefaultFtpSessionFactory ftpClientFactory(){
        DefaultFtpSessionFactory defaultFtpSessionFactory = new DefaultFtpSessionFactory();

        defaultFtpSessionFactory.setHost("localhost");
        defaultFtpSessionFactory.setPort(21);
        defaultFtpSessionFactory.setUsername("ftptest");
        defaultFtpSessionFactory.setPassword("111111");

        return defaultFtpSessionFactory;
    }
}