package com.example.spring_auth_jwt.services.Impl;

import com.example.spring_auth_jwt.dto.JwtResponse;
import com.example.spring_auth_jwt.dto.SignInRequest;
import com.example.spring_auth_jwt.dto.SignUpRequest;
import com.example.spring_auth_jwt.entity.Role;
import com.example.spring_auth_jwt.entity.User;
import com.example.spring_auth_jwt.repository.UserRepository;
import com.example.spring_auth_jwt.services.AuthService;
import com.example.spring_auth_jwt.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest){
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstname());
        user.setLastname(signUpRequest.getLastname());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);

    }

    public JwtResponse signin(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtResponse jwtResponse = new JwtResponse();

        jwtResponse.setToken(jwt);
        jwtResponse.setRefreshToken(refreshToken);
        return jwtResponse;

    }

}
