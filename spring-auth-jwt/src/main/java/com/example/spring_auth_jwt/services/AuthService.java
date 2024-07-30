package com.example.spring_auth_jwt.services;

import com.example.spring_auth_jwt.dto.JwtResponse;
import com.example.spring_auth_jwt.dto.SignInRequest;
import com.example.spring_auth_jwt.dto.SignUpRequest;
import com.example.spring_auth_jwt.entity.User;

public interface AuthService {

    User signup(SignUpRequest signUpRequest);

    JwtResponse signin(SignInRequest signInRequest);

}
