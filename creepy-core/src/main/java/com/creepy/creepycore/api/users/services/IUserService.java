package com.creepy.creepycore.api.users.services;

import com.creepy.creepycore.api.users.dtos.UserDTO;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);

    boolean existsByEmail(String email);

}
