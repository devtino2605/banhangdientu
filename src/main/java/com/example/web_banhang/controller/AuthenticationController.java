package com.example.web_banhang.controller;

import com.example.web_banhang.model.Dto.LoginResponseDto;
import com.example.web_banhang.Services.AuthenticationService;
import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.Dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser register(@RequestBody RegistrationDto body){
        return authenticationService.registerUser(body.getUsername(),body.getEmail(), body.getPhone(), body.getPassword());
    }
    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody RegistrationDto body){
        return authenticationService.loginUser(body.getEmail(),body.getPassword());
    }
}
