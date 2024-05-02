package com.example.web_banhang.Services.Imp;

import com.example.web_banhang.Services.ProductService;
import com.example.web_banhang.exception.ResourceNotFoundException;
import com.example.web_banhang.model.Product;
import com.example.web_banhang.reponsibility.ProductReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductReponsitory productReponsitory;
    @Override
    public Product add(Product Product) {
        Product.setCreatedAt(LocalDateTime.now());
        return productReponsitory.save(Product);
    }

    @Override
    public Product update(int id, Product Product) {
        Product product = productReponsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("not find product with id "+id));
        product.setId(id);
        product.setCategory(Product.getCategory());
        product.setTitle(Product.getTitle());
        product.setPrice(Product.getPrice());
        product.setDiscount(Product.getDiscount());
        product.setThumbnail(Product.getThumbnail());

        product.setUpdatedAt(LocalDateTime.now());
        product.setDeleted(Product.getDeleted());

        return productReponsitory.save(product);
    }

    @Override
    public void delete(int id) {
        if(productReponsitory.findById(id).isPresent()){
            productReponsitory.deleteById(id);
        }else {
            new ResourceNotFoundException("not find product with id "+id);
        }
    }

    @Override
    public Product getProduct(int id) {
        Product product = productReponsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("not find product with id "+id));
        return product;
    }

    @Override
    public List<Product> getAll() {
        return productReponsitory.findAll();
    }
}
