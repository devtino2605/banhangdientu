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
    public ProductRvDto addRv(ProductReview productReview) {
        productReview.setCreatedAt(LocalDateTime.now());
        ProductReview saveprv = productRvReponsitory.save(productReview);
        return ProductRvMapper.mapToDto(saveprv);
    }

    @Override
    public ProductRvDto update(int id,ProductReview productReview) {
        Optional<ProductReview> optionalProductReview = productRvReponsitory.findById(id);
        if (optionalProductReview.isPresent()) {
            ProductReview existingProductReview = optionalProductReview.get();
            existingProductReview.setRating(productReview.getRating());
            existingProductReview.setUpdatedAt(LocalDateTime.now());
            // Lưu đối tượng cập nhật vào cơ sở dữ liệu
            ProductReview updatedProductReview = productRvReponsitory.save(existingProductReview);
            return ProductRvMapper.mapToDto(updatedProductReview);
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng cần cập nhật
            // Ví dụ: throw một ngoại lệ hoặc trả về null
            return null;
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
