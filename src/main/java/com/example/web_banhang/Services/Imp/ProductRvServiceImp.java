package com.example.web_banhang.Services.Imp;

import com.example.web_banhang.Services.ProductRvService;
import com.example.web_banhang.exception.ResourceNotFoundException;
import com.example.web_banhang.mapper.ProductRvMapper;
import com.example.web_banhang.model.Dto.ProductDto;
import com.example.web_banhang.model.Dto.ProductRvDto;
import com.example.web_banhang.model.Product;
import com.example.web_banhang.model.ProductReview;
import com.example.web_banhang.reponsibility.ProductReponsitory;
import com.example.web_banhang.reponsibility.ProductRvReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductRvServiceImp implements ProductRvService {
    @Autowired
    private ProductRvReponsitory productRvReponsitory;
    @Autowired
    private ProductReponsitory productReponsitory;

    @Override
    public ProductRvDto addOrUpdateRv(ProductReview productReview) {
        Optional<ProductReview> productRv = productRvReponsitory.findByProductIdAndUserId(productReview.getProduct().getId(),productReview.getUser().getUserId());

        if (productRv.isPresent()){
            ProductReview existPv = productRv.get();
            existPv.setRating(productReview.getRating());
            existPv.setUpdatedAt(LocalDateTime.now());
            ProductReview updatePv = productRvReponsitory.save(existPv);
            return ProductRvMapper.mapToDto(updatePv);
        }else {
            ProductReview newPv = new ProductReview();
            newPv.setUser(productReview.getUser()); // Set user
            newPv.setProduct(productReview.getProduct()); // Set product
            newPv.setRating(productReview.getRating());
            newPv.setCreatedAt(LocalDateTime.now());

            ProductReview savedReview = productRvReponsitory.save(newPv);

            return ProductRvMapper.mapToDto(savedReview);
        }
    }


    @Override
    public List<ProductRvDto> getAllByIdProduct(int productId) {
        List<ProductReview> productReviews = productRvReponsitory.findAllByProductId(productId);
        List<ProductRvDto> productRvDtos = productReviews.stream()
                .map(ProductRvMapper::mapToDto)
                .collect(Collectors.toList());
        return productRvDtos;
    }
}
