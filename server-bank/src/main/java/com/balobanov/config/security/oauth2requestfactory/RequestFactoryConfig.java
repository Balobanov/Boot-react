package com.balobanov.config.security.oauth2requestfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;

@Configuration
public class RequestFactoryConfig {

    /**
     * To auth user from facebook
     * @param clientDetailsService
     * @return
     */
    @Autowired
    @Bean
    public DefaultOAuth2RequestFactory defaultOAuth2RequestFactory(ClientDetailsService clientDetailsService){
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }
}
