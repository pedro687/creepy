package com.creepy.creepycore.api.users.service;

import com.creepy.creepycore.api.users.dtos.UserDTO;
import com.creepy.creepycore.api.users.repositories.UserRepository;
import com.creepy.creepycore.api.users.services.IUserService;
import com.creepy.creepycore.api.users.services.impl.UserService;
import com.creepy.creepycore.api.users.utils.UserConverter;
import com.creepy.creepycore.shared.configurations.PasswordEncoder;
import com.creepy.creepycore.shared.domain.user.User;
import com.creepy.creepycore.shared.domain.user.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.print.Book;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    UserConverter userConverter;

    @MockBean
    PasswordEncoder passwordEncoder;

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

   /* @BeforeEach
    public void setUp() {
        this.userService = new UserService(userConverter, userRepository);
    }*/

    @Test
    @DisplayName("Deve registrar um usuário na base de dados")
    void shouldBeAbleRegisterAnUserInDatabase() {
        //cenário
        UserDTO userDTO = createUserDto();

        User user = userConverter.toEntity(userDTO);

        //Execução
        Mockito.when(userRepository.save(user))
                .thenReturn(user);

        UserDTO savedUser = userService.createUser(userDTO);

        Assertions.assertEquals(savedUser.getUsername(), user.getUsername());
    }
}
