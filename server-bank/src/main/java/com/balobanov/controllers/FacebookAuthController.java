package com.balobanov.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "facebook")
public class FacebookAuthController {

    @RequestMapping(method = RequestMethod.POST)
    public void authPost(){
    }
}
