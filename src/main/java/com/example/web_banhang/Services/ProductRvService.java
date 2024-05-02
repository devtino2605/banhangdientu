package com.example.web_banhang.Services;

import com.example.web_banhang.model.Dto.ProductDto;
import com.example.web_banhang.model.Dto.ProductRvDto;
import com.example.web_banhang.model.ProductReview;

import java.util.List;

public interface ProductRvService {
    ProductRvDto addRv(ProductReview productReview);
    ProductRvDto update(int id,ProductReview productReview);

    List<ProductRvDto> getAllByIdProduct(int productId);
}
