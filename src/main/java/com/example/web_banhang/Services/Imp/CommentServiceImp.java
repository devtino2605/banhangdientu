package com.example.web_banhang.Services.Imp;

import com.example.web_banhang.Services.CommentService;
import com.example.web_banhang.model.Comment;
import com.example.web_banhang.reponsibility.CommentReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private CommentReponsitory commentReponsitory;
    @Autowired
    private ProductServiceImp productServiceImp;

    @Override
    public Comment createComment(Comment Comment) {
        return commentReponsitory.save(Comment);
    }

    @Override
    public List<Comment> getAllComment(int idProduct) {
        return commentReponsitory.findAllByProduct(productServiceImp.getProduct(idProduct));
    }

    @Override
    public Comment updatwComment(Comment updateComment) {
        return commentReponsitory.save(updateComment);
    }

    @Override
    public void deleteComment(int id) {
         commentReponsitory.delete(commentReponsitory.findById(id));
    }

    @Override
    public Comment getCommentById(int id) {
        return commentReponsitory.findById(id);
    }

}
