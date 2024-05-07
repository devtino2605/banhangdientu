package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailReponsitory extends JpaRepository<OrderDetails,Integer> {
}
