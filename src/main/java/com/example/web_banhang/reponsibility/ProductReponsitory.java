package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReponsitory extends JpaRepository<Product,Integer> {
}
