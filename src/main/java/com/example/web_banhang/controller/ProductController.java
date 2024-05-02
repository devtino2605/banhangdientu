package com.example.web_banhang.controller;

import com.example.web_banhang.Services.Imp.ProductServiceImp;
import com.example.web_banhang.Services.ProductService;
import com.example.web_banhang.model.Product;
import com.example.web_banhang.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class ProductController {
    
    @Autowired
    private ProductServiceImp productServiceImp;
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> categories = productServiceImp.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int id){
        return ResponseEntity.ok(productServiceImp.getProduct(id));
    }

    @PostMapping("/product/add")
    public ResponseEntity<Product> add(@RequestBody Product Product){
        Product saveCate = productServiceImp.add(Product);
        return new ResponseEntity<>(saveCate, HttpStatus.CREATED);
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<Product> updateEmp(@PathVariable("id") int id, @RequestBody Product Product){
        Product updateProduct = productServiceImp.update(id , Product);
        return ResponseEntity.ok(updateProduct);
    }


    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        productServiceImp.delete(id);
        return ResponseEntity.ok("delete Product success");
    }
    
}
