package com.creepy.creepycore.api.users.dtos;

import com.creepy.creepycore.shared.domain.user.enums.Gender;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String tellphone;

    @NotNull
    private String country;

    @NotNull
    private String uf;

    @NotNull
    private String city;

    @NotNull
    private Gender gender;
}
