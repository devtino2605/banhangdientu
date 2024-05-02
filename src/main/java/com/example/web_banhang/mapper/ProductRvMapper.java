package com.example.web_banhang.mapper;

import com.example.web_banhang.model.Dto.ProductDto;
import com.example.web_banhang.model.Dto.ProductRvDto;
import com.example.web_banhang.model.ProductReview;

public class ProductRvMapper {
    public static ProductRvDto mapToDto(ProductReview productReview)
    {
        return new ProductRvDto(
                productReview.getId(),
                productReview.getProduct().getTitle(),
                productReview.getRating(),
                productReview.getUser().getUsername()
        );
    }
}
