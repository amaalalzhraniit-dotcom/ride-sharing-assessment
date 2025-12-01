package com.example.ridesharingsystem.business.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {

    private String username;

    private String password;

    private String email;

    private String phone;

    private Integer roleId;

    private String fullName;
}
