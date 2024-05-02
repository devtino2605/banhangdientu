package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReponsitory extends JpaRepository<Orders,Integer> {
}
