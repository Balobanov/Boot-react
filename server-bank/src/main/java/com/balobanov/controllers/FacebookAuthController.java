package com.balobanov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@RestController
@RequestMapping(value = "facebook")
public class FacebookAuthController {

    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.POST)
    public LinkedHashMap authGetUser(@RequestParam String accessToken){
        String s = "https://graph.facebook.com/me?access_token={#token}&fields=about, name, email, picture, birthday, gender, last_name, first_name, middle_name";
        return restTemplate.getForObject(s.replace("{#token}", accessToken), LinkedHashMap.class);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}