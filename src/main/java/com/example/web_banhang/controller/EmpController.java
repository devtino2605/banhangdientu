package com.example.web_banhang.controller;

import com.example.web_banhang.model.Dto.EmpDto;
import com.example.web_banhang.Services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@Controller
@RequestMapping("/api/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/hello")
    public String hello(){
        return "index";
    }
    //Add emp Rest API
    @PostMapping("/add")
    public ResponseEntity<EmpDto> createEmp(@RequestBody EmpDto empDto){
        EmpDto savedEmp = empService.createEmpDto(empDto);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }

    //get emp by id
    @GetMapping("/{id}")
    public ResponseEntity<EmpDto> getEmpById(@PathVariable("id") int idEmp){
        EmpDto empDto = empService.getEmpById(idEmp);
        return ResponseEntity.ok(empDto);
    }

    //get all emp
    @GetMapping
    public ResponseEntity<List<EmpDto>> getAllEmp(){
        List<EmpDto> empDtos = empService.getAllEmp();
        return ResponseEntity.ok(empDtos);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmpDto> updateEmp(@PathVariable("id") int id,@RequestBody EmpDto empDto){
        EmpDto empDto1 = empService.updatwEmp(id , empDto);
        return ResponseEntity.ok(empDto1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        empService.deleteEmp(id);
        return ResponseEntity.ok("delete emp success");
    }
}
