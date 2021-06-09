package com.creepy.authcreepy.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

}