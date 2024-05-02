package com.example.web_banhang.mapper;

import com.example.web_banhang.model.Dto.EmpDto;
import com.example.web_banhang.model.Users;

public class EmpMapper {
    public static EmpDto mapToEmp(Users user){
        return new EmpDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
                user.getGioitinh(),
                user.getAddress()
        );
    }
    public static Users mapToUser(EmpDto empDto){
        return  new Users(
                empDto.getId(),
                empDto.getName(),
                empDto.getEmail(),
                empDto.getGioitinh(),
                empDto.getAddress()
        );
    }
}
