package com.example.web_banhang.Services.Imp;

import com.example.web_banhang.Services.CategoryService;
import com.example.web_banhang.exception.ResourceNotFoundException;
import com.example.web_banhang.model.Category;
import com.example.web_banhang.model.Users;
import com.example.web_banhang.reponsibility.CategoryReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryReponsitory categoryReponsitory;
    @Override
    public Category add(Category category) {
        return categoryReponsitory.save(category);
    }

    @Override
    public Category update(int id, Category category) {
        Category category1=categoryReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("category not exist with id" + id));

        category1.setId(id);
        category1.setName(category.getName());
        return categoryReponsitory.save(category1);
    }

    @Override
    public void delete(int id) {
        Category category=categoryReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("category not exist with id" + id));
        categoryReponsitory.delete(category);
    }

    @Override
    public Category getCategory(int id) {
        return categoryReponsitory.findById(id).orElseThrow(()-> new ResourceNotFoundException("category not exist with id" + id));
    }

    @Override
    public List<Category> getAll() {
        return categoryReponsitory.findAll();
    }
}
