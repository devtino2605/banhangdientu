package com.example.web_banhang.controller;

import com.example.web_banhang.model.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/api/admin")
@RestController
public class AdminController {
    private final ObjectMapper objectMapper;

    public AdminController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/user")
    public String getCurrentUser(HttpServletRequest request) {
        String username = (String) request.getAttribute("email");

        if (username != null) {
            return "Hi, " + username + "! Welcome back.";
        } else {
            return "You are not logged in.";
        }
    }
}
