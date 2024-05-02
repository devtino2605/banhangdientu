package com.example.web_banhang.Services.Imp;

import com.example.web_banhang.model.Dto.EmpDto;
import com.example.web_banhang.Services.EmpService;
import com.example.web_banhang.exception.ResourceNotFoundException;
import com.example.web_banhang.mapper.EmpMapper;
import com.example.web_banhang.model.Users;
import com.example.web_banhang.reponsibility.EmpReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImp implements EmpService {

    @Autowired
    private EmpReponsitory empReponsitory;
    @Override
    public EmpDto createEmpDto(EmpDto empDto) {
        Users user = EmpMapper.mapToUser(empDto);
        Users  savedUser =  empReponsitory.save(user);
        return EmpMapper.mapToEmp(savedUser);
    }

    @Override
    public EmpDto getEmpById(int id) {
        Users user = empReponsitory.findById(id).orElseThrow(() -> new ResourceNotFoundException("emp not exist with id :"+id));
        return EmpMapper.mapToEmp(user);
    }

    @Override
    public List<EmpDto> getAllEmp() {
        List<Users> list = empReponsitory.findAll();
        return list.stream().map((users) ->EmpMapper.mapToEmp(users)).collect(Collectors.toList());
    }

    @Override
    public EmpDto updatwEmp(int id, EmpDto updateEmp) {
        Users user =empReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("emp not exist with id" + id));

        user.setName(updateEmp.getName());
        user.setEmail(updateEmp.getEmail());
        user.setGioitinh(updateEmp.getGioitinh());
        user.setAddress(updateEmp.getAddress());

        Users updateUserDb = empReponsitory.save(user);
        return EmpMapper.mapToEmp(updateUserDb);
    }

    @Override
    public void deleteEmp(int id) {
        empReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("emp not exist with id" +id));
        empReponsitory.deleteById(id);
    }
}
