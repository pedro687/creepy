package com.creepy.creepycore.api.users.services.impl;

import com.creepy.creepycore.api.users.dtos.UserDTO;
import com.creepy.creepycore.api.users.repositories.UserRepository;
import com.creepy.creepycore.api.users.services.IUserService;
import com.creepy.creepycore.api.users.utils.UserConverter;
import com.creepy.creepycore.shared.configurations.PasswordEncoder;
import com.creepy.creepycore.shared.domain.user.User;
import com.creepy.creepycore.shared.exceptions.EmailAlreadyExistException;
import com.creepy.creepycore.shared.infra.utils.EmailValidator;
import com.creepy.creepycore.shared.infra.utils.TellphoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserConverter userConverter;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserConverter userConverter, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO createUser(UserDTO userDTO) {
        try {
            new EmailValidator(userDTO.getEmail());
            new TellphoneValidator(userDTO.getTellphone());
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }

        if (existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistException("Email Already Exist!");
        }

        userDTO.setPassword(passwordEncoder.PasswordEncoder().encode(userDTO.getPassword()));

        User user = userConverter.toEntity(userDTO);

        User createdUser = userRepository.save(user);

        return userConverter.toDto(createdUser);
    }
}