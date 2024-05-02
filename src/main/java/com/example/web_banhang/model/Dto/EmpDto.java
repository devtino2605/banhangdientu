package com.example.web_banhang.model.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpDto {
    private int id;
    private String name;
    private String email;
    private String gioitinh;
    private String address;

}
