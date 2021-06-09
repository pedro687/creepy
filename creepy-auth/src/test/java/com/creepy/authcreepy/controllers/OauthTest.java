package com.creepy.authcreepy.controllers;


import com.creepy.authcreepy.dto.UsersDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OauthTest {
    static String OAUTH_URL = "/oauth/token";

    @Autowired
    MockMvc mvc;

    @Autowired
    PasswordEncoder passwordEncode;

    public UsersDTO createUserDTO() {
        UsersDTO userDTO = new UsersDTO();
        userDTO.setEmail("pedrosilva@gmail.com");
        userDTO.setUsername("pedrosilva");
        userDTO.setPassword(passwordEncode.encode("12345"));

        return userDTO;
    }

    @Test
    @DisplayName("Deve efetuar login")
    void shouldLogin() throws Exception{
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client", "angular");
        params.add("username", createUserDTO().getEmail());
        params.add("password", "12345");

        MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post(OAUTH_URL)
                .params(params)
                .with(httpBasic("creepy-core","react123"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mvc.perform(req)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("access_token").exists());
    }
}
