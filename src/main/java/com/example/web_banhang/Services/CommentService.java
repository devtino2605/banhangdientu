package com.example.web_banhang.Services;

import com.example.web_banhang.model.Comment;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment createComment(Comment Comment);

    List<Comment> getAllComment(int idProduct);

    Comment updatwComment( Comment updateComment);

    void deleteComment(int id);
    Comment getCommentById(int id);
}
