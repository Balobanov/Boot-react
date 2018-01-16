package com.balobanov.controllers;

import com.balobanov.models.User;
import com.balobanov.services.abstraction.RoleService;
import com.balobanov.services.abstraction.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "facebook")
public class FacebookAuthController {

    private RestTemplate restTemplate;
    private RoleService roleService;
    private DefaultOAuth2RequestFactory defaultOAuth2RequestFactory;
    private ClientDetailsService clientDetailsService;
    private DefaultTokenServices defaultTokenServices;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    @Autowired
    @Qualifier(value = "authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Value("${client.id}")
    private String clientId;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public OAuth2AccessToken authGetUser(@RequestBody Map<String, String> params){
        String s = "https://graph.facebook.com/me?access_token={#token}&fields=about, name, email, picture, birthday, gender, last_name, first_name, middle_name";
        LinkedHashMap forObject = restTemplate.getForObject(s.replace("{#token}", params.get("access_token")), LinkedHashMap.class);

        User newUser = new User();
        newUser.setFirstName((String) forObject.get("first_name"));
        newUser.setLastName((String) forObject.get("last_name"));
        newUser.setRoles(roleService.getByRoles("ROLE_USER"));
        newUser.setEmail((String) forObject.get("email"));
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        String randomPassword = RandomStringUtils.random(10, false, true);
        newUser.setPassword(passwordEncoder.encode(randomPassword));

        newUser = userService.save(newUser);

        Map<String, String> requestparams = new HashMap<>();
        requestparams.put("grant_type", "password");
        requestparams.put("username", (String) forObject.get("email"));
        requestparams.put("password", randomPassword);

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = defaultOAuth2RequestFactory.createTokenRequest(requestparams, clientDetails);

        OAuth2Request storedOAuth2Request = defaultOAuth2RequestFactory.createOAuth2Request(clientDetails, tokenRequest);
        Authentication userAuth = new UsernamePasswordAuthenticationToken(forObject.get("email"), randomPassword, newUser.getAuthorities());
        authenticationManager.authenticate(userAuth);
        requestparams.remove("password");
        ((AbstractAuthenticationToken) userAuth).setDetails(requestparams);

        return defaultTokenServices.createAccessToken(new OAuth2Authentication(storedOAuth2Request, userAuth));
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Autowired
    public void setDefaultOAuth2RequestFactory(DefaultOAuth2RequestFactory defaultOAuth2RequestFactory) {
        this.defaultOAuth2RequestFactory = defaultOAuth2RequestFactory;
    }

    @Autowired
    public void setDefaultTokenServices(DefaultTokenServices defaultTokenServices) {
        this.defaultTokenServices = defaultTokenServices;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}