package com.example.web_banhang.model.Dto;

import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.RefreshToken;
import lombok.Builder;


@Builder
public class LoginResponseDto {
    private ApplicationUser user;

    private String accessToken;

    private String refreshJwt;
    public LoginResponseDto(){
        super();
    }

    public LoginResponseDto(ApplicationUser user, String jwt,String refreshJwt){
        this.user =user;
        this.accessToken =jwt;
        this.refreshJwt = refreshJwt;
    }

    public void setUser(ApplicationUser user){
        this.user =user;
    }
    public ApplicationUser getUser(){
        return this.user;
    }
    public void setJwt(String jwt)
    {
        this.accessToken =jwt;
    }
    public String getJwt(){
        return this.accessToken;
    }
}
