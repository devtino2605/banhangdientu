package com.example.web_banhang.controller;


import com.example.web_banhang.Services.Imp.ProductRvServiceImp;
import com.example.web_banhang.model.Dto.ProductRvDto;
import com.example.web_banhang.model.ProductReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class ProductRvController {
    @Autowired
    private ProductRvServiceImp productRvServiceImp;

    @GetMapping("/review/{productId}")
    public ResponseEntity<List<ProductRvDto>> getall(@PathVariable("productId") int productId){
        return ResponseEntity.ok(productRvServiceImp.getAllByIdProduct(productId));
    }
    @PostMapping("/review/addReview")
    public ResponseEntity<ProductRvDto> addOrUpdate(@RequestBody ProductReview productReview){
        ProductRvDto saveRv = productRvServiceImp.addOrUpdateRv(productReview);
        System.out.println(saveRv.getId());
        return new ResponseEntity<>(saveRv, HttpStatus.CREATED);
    }
}