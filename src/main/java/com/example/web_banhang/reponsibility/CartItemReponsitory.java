package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemReponsitory extends JpaRepository<CartItem,Integer> {
    List<CartItem> findAllByUser(ApplicationUser user);
}

