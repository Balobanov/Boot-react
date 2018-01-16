package com.balobanov.config.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Common {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
