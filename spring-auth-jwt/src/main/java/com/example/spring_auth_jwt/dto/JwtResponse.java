package com.example.spring_auth_jwt.dto;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;

    private String refreshToken;

}
