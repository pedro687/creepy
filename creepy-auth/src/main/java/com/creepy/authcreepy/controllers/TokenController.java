package com.creepy.authcreepy.controllers;

import com.creepy.authcreepy.dto.UsersDTO;
import com.creepy.authcreepy.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class TokenController {

    @Autowired
    private UsersService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UsersDTO usersDTO) {
        userService.create(usersDTO);
    }
}
