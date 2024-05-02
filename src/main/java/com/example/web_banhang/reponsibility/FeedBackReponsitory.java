package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackReponsitory extends JpaRepository<FeedBack,Integer> {
}
