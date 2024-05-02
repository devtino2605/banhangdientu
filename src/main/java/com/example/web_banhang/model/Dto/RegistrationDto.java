package com.example.web_banhang.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RegistrationDto {
    private String username;
    private String email;
    private String phone;
    private String password;

    public RegistrationDto(){
        super();
    }

    public RegistrationDto(String username,String email,String phone, String password){
        super();
        this.email = email;
        this.password = password;
        this.username= username;
        this.phone = phone;
    }

    public String getUsername(){
        return this.username;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "Registration info: email: " + this.email + " password: " + this.password;
    }
}
