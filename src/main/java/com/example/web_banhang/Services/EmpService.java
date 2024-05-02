package com.example.web_banhang.Services;

import com.example.web_banhang.model.Dto.EmpDto;

import java.util.List;

public interface EmpService {
    EmpDto createEmpDto(EmpDto empDto);
    EmpDto getEmpById(int id);
    List<EmpDto> getAllEmp();

    EmpDto updatwEmp(int id, EmpDto updateEmp);

    void deleteEmp(int id);
}
