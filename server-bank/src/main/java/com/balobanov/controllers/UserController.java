package com.balobanov.controllers;

import com.balobanov.models.User;
import com.balobanov.services.abstraction.RoleService;
import com.balobanov.services.abstraction.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserDetailService userDetailService;

    private RoleService roleService;

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = "application/json")
    public UserDetails me(Principal principal) {
        return userDetailService.loadUserByUsername(principal.getName());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = "application/json")
    public void newUser(@RequestBody Map<String, ?> params) {
        String firstName = (String) params.get("firstName");
        String lastName = (String) params.get("lastName");
        String password = (String) params.get("password");
        String email = (String) params.get("email");

        User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setPassword(password);
            newUser.setEmail(email);

            newUser.setAccountNonExpired(true);
            newUser.setAccountNonLocked(true);
            newUser.setCredentialsNonExpired(true);
            newUser.setEnabled(true);

            newUser.setRoles(roleService.getByRoles("ROLE_USER"));

        userDetailService.save(newUser);
    }

    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
