package com.nemo.userservice.controller;

import com.nemo.userservice.dto.AuthRequest;
import com.nemo.userservice.service.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/normalAccess")
public class AuthController {
private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
    }



}
