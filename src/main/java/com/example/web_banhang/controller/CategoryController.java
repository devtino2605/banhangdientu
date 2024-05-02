package com.example.web_banhang.controller;

import com.example.web_banhang.Services.Imp.CategoryServiceImp;
import com.example.web_banhang.model.Category;
import com.example.web_banhang.model.Dto.EmpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = categoryServiceImp.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") int id){
        return ResponseEntity.ok(categoryServiceImp.getCategory(id));
    }

    @PostMapping("/category/add")
    public ResponseEntity<Category> add(@RequestBody Category category){
        Category saveCate = categoryServiceImp.add(category);
        return new ResponseEntity<>(saveCate, HttpStatus.CREATED);
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<Category> updateEmp(@PathVariable("id") int id, @RequestBody Category category){
        Category updateCategory = categoryServiceImp.update(id , category);
        return ResponseEntity.ok(updateCategory);
    }


    @DeleteMapping("/category/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        categoryServiceImp.delete(id);
        return ResponseEntity.ok("delete category success");
    }
}
