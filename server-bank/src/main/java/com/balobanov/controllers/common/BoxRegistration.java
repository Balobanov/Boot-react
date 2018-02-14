package com.balobanov.controllers.common;

import com.balobanov.models.User;
import com.balobanov.services.abstraction.RoleService;
import com.balobanov.services.abstraction.UserService;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/box")
public class BoxRegistration {

    private RestTemplate restTemplate;
    private RoleService roleService;
    private DefaultOAuth2RequestFactory defaultOAuth2RequestFactory;
    private ClientDetailsService clientDetailsService;
    private DefaultTokenServices defaultTokenServices;
    private PasswordEncoder passwordEncoder;
    private JdbcTokenStore jdbcTokenStore;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier(value = "authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Value("${client.id}")
    private String clientId;

    @Value("${box.client.id}")
    private String boxClientId;

    @Value("${box.client.secret}")
    private String client_secret;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/registration")
    public OAuth2AccessToken authGetUser(@RequestBody Map<String, ?> params) {
        String access_token = (String) params.get("access_token");
        String refresh_token = (String) params.get("refresh_token");

        BoxAPIConnection api = new BoxAPIConnection(boxClientId,
                client_secret, access_token, refresh_token);

        BoxUser user = BoxUser.getCurrentUser(api);
        BoxUser.Info info = user.getInfo();

        User newUser = new User();
        newUser.setFirstName(info.getName());
        newUser.setRoles(roleService.getByRoles("ROLE_USER"));
        newUser.setEmail(info.getLogin());
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        String randomPassword = RandomStringUtils.random(10, false, true);
        newUser.setPassword(passwordEncoder.encode(randomPassword));

        newUser = userService.save(newUser);

        Map<String, String> requestparams = new HashMap<>();
        requestparams.put("grant_type", "password");
        requestparams.put("username", info.getLogin());
        requestparams.put("password", randomPassword);

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        TokenRequest tokenRequest = defaultOAuth2RequestFactory.createTokenRequest(requestparams, clientDetails);

        OAuth2Request storedOAuth2Request = defaultOAuth2RequestFactory.createOAuth2Request(clientDetails, tokenRequest);
        Authentication userAuth = new UsernamePasswordAuthenticationToken(info.getLogin(), randomPassword, newUser.getAuthorities());

        authenticationManager.authenticate(userAuth);

        requestparams.remove("password");
        ((AbstractAuthenticationToken) userAuth).setDetails(requestparams);

        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(storedOAuth2Request, userAuth);
//      OAuth2AccessToken accessToken = defaultTokenServices.createAccessToken(oAuth2Authentication);

        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken(access_token);
//        accessToken.setExpiration(new Date(System.currentTimeMillis() + (clientDetails.getAccessTokenValiditySeconds() * 1000L)));
        DefaultOAuth2RefreshToken defaultOAuth2RefreshToken = new DefaultOAuth2RefreshToken(refresh_token);
        accessToken.setRefreshToken(defaultOAuth2RefreshToken);
        accessToken.setScope(oAuth2Authentication.getOAuth2Request().getScope());

        jdbcTokenStore.storeAccessToken(accessToken, oAuth2Authentication);
        jdbcTokenStore.storeRefreshToken(defaultOAuth2RefreshToken, oAuth2Authentication);

        return accessToken;
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

    @Autowired
    public void setJdbcTokenStore(JdbcTokenStore jdbcTokenStore) {
        this.jdbcTokenStore = jdbcTokenStore;
    }
}
