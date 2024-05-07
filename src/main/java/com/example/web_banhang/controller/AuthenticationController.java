package com.example.web_banhang.controller;

import com.example.web_banhang.Services.RefreshTokenSerivice;
import com.example.web_banhang.Services.TokenService;
import com.example.web_banhang.model.Dto.JwtResponse;
import com.example.web_banhang.model.Dto.LoginResponseDto;
import com.example.web_banhang.Services.AuthenticationService;
import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.Dto.RefreshTokenRequest;
import com.example.web_banhang.model.Dto.RegistrationDto;
import com.example.web_banhang.model.RefreshToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RefreshTokenSerivice refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ApplicationUser register(@RequestBody RegistrationDto body){
        return authenticationService.registerUser(body.getUsername(),body.getEmail(), body.getPhone(), body.getPassword());
    }
    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody RegistrationDto body){
        return authenticationService.loginUser(body.getEmail(),body.getPassword());
    }


    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {

        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    Authentication auth = new UsernamePasswordAuthenticationToken(userInfo.getUsername(), null, userInfo.getAuthorities());
;
                    String accessToken = tokenService.generateJwt(auth);
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"));
    }
}
