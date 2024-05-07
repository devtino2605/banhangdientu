package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Dto.ProductDto;
import com.example.web_banhang.model.Dto.ProductRvDto;
import com.example.web_banhang.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductRvReponsitory extends JpaRepository<ProductReview,Integer> {
    @Query("SELECT p FROM ProductReview p WHERE p.product.id = :productId")
    List<ProductReview> findAllByProductId(int productId);

    @Query("SELECT pv FROM ProductReview pv WHERE pv.product.id = :productId AND pv.user.id = :userId")
    Optional<ProductReview> findByProductIdAndUserId(@Param("productId") int productId, @Param("userId") int userId);


}
