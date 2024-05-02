package com.example.web_banhang.Services;

import com.example.web_banhang.model.Product;

import java.util.List;

public interface ProductService {
    public Product add(Product Product);
    public Product update(int id,Product Product);
    public void delete(int id);
    public Product getProduct(int id);
    public List<Product> getAll();
}
