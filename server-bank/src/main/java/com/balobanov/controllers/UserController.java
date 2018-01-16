package com.balobanov.controllers;

import com.balobanov.exceptions.ApplicationException;
import com.balobanov.exceptions.EmailDoesNotExists;
import com.balobanov.services.abstraction.AuthFlowService;
import com.balobanov.services.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    private AuthFlowService authFlowService;

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = "application/json")
    public UserDetails me(Principal principal) {
        return userService.loadUserByUsername(principal.getName());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = "application/json")
    public void newUser(@RequestBody Map<String, ?> params) {
        authFlowService.registration(params);
    }

    @RequestMapping(value = "/password/request", method = RequestMethod.POST)
    public void restorePasswordRequest(@RequestBody Map<String, ?> params) throws EmailDoesNotExists, MessagingException {
        String email = (String) params.get("email");
        authFlowService.newPasswordRequest(email);
    }

    @RequestMapping(value = "/password/restore", method = RequestMethod.POST)
    public void restorePasswordRestore(@RequestBody Map<String, ?> params) throws ApplicationException, MessagingException {
        String email = (String) params.get("email");
        String code = (String) params.get("code");
        authFlowService.newPasswordRestore(code,email);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthFlowService(AuthFlowService authFlowService) {
        this.authFlowService = authFlowService;
    }
}
