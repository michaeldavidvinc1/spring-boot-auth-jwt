package com.example.spring_auth_jwt.dto;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;

    private String password;

}
