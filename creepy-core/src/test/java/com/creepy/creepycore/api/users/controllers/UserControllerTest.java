package com.creepy.creepycore.api.users.controllers;

import com.creepy.creepycore.api.users.dtos.UserDTO;
import com.creepy.creepycore.api.users.services.IUserService;
import com.creepy.creepycore.api.users.utils.UserConverter;
import com.creepy.creepycore.shared.configurations.PasswordEncoder;
import com.creepy.creepycore.shared.domain.user.User;
import com.creepy.creepycore.shared.domain.user.enums.Gender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    private static String BASE_URL = "/user";

    @MockBean
    IUserService userService;

    @Autowired
    MockMvc mvc;

    @MockBean
    UserConverter userConverter;

    public UserDTO createUserDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUf("SP");
        userDTO.setPassword("12345");
        userDTO.setCity("São Paulo");
        userDTO.setUsername("Jon Doe");
        userDTO.setCountry("Brazil");
        userDTO.setEmail("JonDoe@gmail.com");
        userDTO.setTellphone("13996403088");
        userDTO.setGender(Gender.M);

        return userDTO;
    }

    @Test
    @DisplayName("Deve cadastrar um usuário")
    void shouldBeAbleToCreateANewUser() throws Exception {
        UserDTO userDTO = createUserDto();
        String json = new ObjectMapper().writeValueAsString(userDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(BASE_URL.concat("/register"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        BDDMockito.given(userService.createUser(Mockito.any(UserDTO.class))).willReturn(userDTO);

        mvc.perform(request)
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("username").value(userDTO.getUsername()))
            .andExpect(MockMvcResultMatchers.jsonPath("email").value(userDTO.getEmail()));
    }
}
