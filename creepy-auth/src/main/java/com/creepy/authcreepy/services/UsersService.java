package com.creepy.authcreepy.services;

import com.creepy.authcreepy.config.security.PasswordEncodeConfig;
import com.creepy.authcreepy.dto.UsersDTO;
import com.creepy.authcreepy.models.User;
import com.creepy.authcreepy.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncodeConfig encode;

    public void create(UsersDTO usersDTO) {
        var modelMapper = new ModelMapper();
        var user = modelMapper.map(usersDTO, User.class);
        user.setPassword(encode.passwordEncoder().encode(usersDTO.getPassword()));
        userRepository.save(user);
    }
}
