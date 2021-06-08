package com.creepy.creepycore.api.users.utils;

import com.creepy.creepycore.api.users.dtos.UserDTO;
import com.creepy.creepycore.shared.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setUf(userDTO.getUf());
        user.setTellphone(userDTO.getTellphone());
        user.setGender(userDTO.getGender());

        return user;
    }

    public UserDTO toDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setCity(user.getCity());
        userDto.setCountry(user.getCountry());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setUf(user.getUf());
        userDto.setTellphone(user.getTellphone());
        userDto.setGender(user.getGender());

        return userDto;
    }
}
