package com.example.web_banhang.Services;

import com.example.web_banhang.model.Category;

import java.util.List;

public interface CategoryService {
    public Category add(Category category);
    public Category update(int id,Category category);
    public void delete(int id);
    public Category getCategory(int id);
    public List<Category> getAll();
}
