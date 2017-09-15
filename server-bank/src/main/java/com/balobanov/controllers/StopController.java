package com.balobanov.controllers;

import com.balobanov.data.models.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StopController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
    public void update(@RequestBody Map<String, ?> params) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("event", "update");
        messagingTemplate.convertAndSend("/topic/credits", new Credit("One", 2), headers);
    }

}
