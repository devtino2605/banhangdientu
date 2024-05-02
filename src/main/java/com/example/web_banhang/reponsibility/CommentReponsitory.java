package com.example.web_banhang.reponsibility;


import com.example.web_banhang.model.Comment;
import com.example.web_banhang.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReponsitory extends JpaRepository<Comment,Integer> {
    List<Comment> findAllByProduct(Product product);
    Comment findById(int id);
}
