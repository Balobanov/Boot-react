package com.balobanov.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class BankControllerTest {

    private MockMvc mockMvc;
    private Map<String, Object> jsonAuth;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();

        String authorization = "Basic Y2xpZW50YXBwOjEyMzQ1Ng==";
        String token = mockMvc.perform(
                post("/oauth/token")
                        .header("Authorization", authorization)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "denis.aimprosoft@gmail.com")
                        .param("password", "NJrccBOLRReJ")
                        .param("grant_type", "password")
                        .param("scope", "read write")
                        .param("client_id", "clientapp")
                        .param("client_secret", "123456"))
                .andReturn().getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        jsonAuth = new HashMap<>();
        jsonAuth = mapper.readValue(token, new TypeReference<Map<String, String>>(){});
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get("/banks?access_token=" + jsonAuth.get("access_token"))).andExpect(status().isOk());
    }

//    @Test
//    public void saveAndGet() throws Exception {
//        mockMvc.perform(post("/banks?access_token=" + jsonAuth.get("access_token"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\": \"test\"}"))
//                .andExpect(status().isOk());
//    }
}
