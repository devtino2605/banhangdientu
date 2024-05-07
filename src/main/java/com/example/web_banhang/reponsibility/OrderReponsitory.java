package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderReponsitory extends JpaRepository<Orders,Integer> {
    @Query("SELECT orders FROM Orders orders WHERE orders.user.id = :userId")
    List<Orders> findAllByUserId(@Param("userId") int userId);

}
