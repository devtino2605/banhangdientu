package com.example.web_banhang.model.Dto;

import com.example.web_banhang.model.ApplicationUser;

public class LoginResponseDto {
    private ApplicationUser user;
    private String jwt;

    public LoginResponseDto(){
        super();
    }

    public LoginResponseDto(ApplicationUser user, String jwt){
        this.user =user;
        this.jwt =jwt;
    }

    public void setUser(ApplicationUser user){
        this.user =user;
    }
    public ApplicationUser getUser(){
        return this.user;
    }
    public void setJwt(String jwt)
    {
        this.jwt =jwt;
    }
    public String getJwt(){
        return this.jwt;
    }
}
