package com.balobanov.controllers;

import com.balobanov.models.User;
import com.balobanov.models.counter.Counter;
import com.balobanov.services.abstraction.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping(value = "/counter")
public class CounterController {

    private CounterService counterService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Counter getCounter(Principal me) {
        User principal = (User) ((OAuth2Authentication) me).getPrincipal();
        return counterService.getByUser(principal);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public void increment(@RequestBody Map<String, Integer> params, Principal me) {
        User principal = (User) ((OAuth2Authentication) me).getPrincipal();
        counterService.updateCounter(principal, params.get("by"));
    }

    @Autowired
    public void setCounterService(CounterService counterService) {
        this.counterService = counterService;
    }
}

