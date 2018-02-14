package com.balobanov.config.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

/**
 * TODO: enable FTP
 */
//@Configuration
//@ImportResource("classpath:integration-ftp.xml")
//@IntegrationComponentScan
//@EnableIntegration
public class Ftp {

    @Value("${ftp.host}")
    private String host;

    @Value("${ftp.port}")
    private Integer port;

    @Value("${ftp.user}")
    private String user;

    @Value("${ftp.password}")
    private String password;

    @Bean(name = "ftpClientFactory")
    public DefaultFtpSessionFactory ftpClientFactory(){
        DefaultFtpSessionFactory defaultFtpSessionFactory = new DefaultFtpSessionFactory();

        defaultFtpSessionFactory.setHost(host);
        defaultFtpSessionFactory.setPort(port);
        defaultFtpSessionFactory.setUsername(user);
        defaultFtpSessionFactory.setPassword(password);

        return defaultFtpSessionFactory;
    }
}