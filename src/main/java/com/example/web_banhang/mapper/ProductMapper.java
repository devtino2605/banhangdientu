package com.example.web_banhang.mapper;

import com.example.web_banhang.model.Dto.EmpDto;
import com.example.web_banhang.model.Dto.ProductDto;
import com.example.web_banhang.model.Product;
import com.example.web_banhang.model.Users;

public class ProductMapper {
    public static ProductDto maptoDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getThumbnail()
        );
    }
}
