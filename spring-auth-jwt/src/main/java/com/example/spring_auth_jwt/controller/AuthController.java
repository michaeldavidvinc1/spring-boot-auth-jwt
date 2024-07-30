package com.example.spring_auth_jwt.controller;

import com.example.spring_auth_jwt.dto.JwtResponse;
import com.example.spring_auth_jwt.dto.SignInRequest;
import com.example.spring_auth_jwt.dto.SignUpRequest;
import com.example.spring_auth_jwt.entity.User;
import com.example.spring_auth_jwt.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> signu(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authService.signin(signInRequest));
    }



}
