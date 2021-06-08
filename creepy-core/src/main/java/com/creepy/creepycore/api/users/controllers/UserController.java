package com.creepy.creepycore.api.users.controllers;

import com.creepy.creepycore.api.users.dtos.UserDTO;
import com.creepy.creepycore.api.users.resources.UserResource;
import com.creepy.creepycore.api.users.services.IUserService;
import com.creepy.creepycore.shared.exceptions.EmailAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserResource {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> create(UserDTO userDTO) {
       try {
           return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
       }catch (EmailAlreadyExistException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }
}
