package com.example.web_banhang.controller;

import com.example.web_banhang.Services.Imp.CommentServiceImp;
import com.example.web_banhang.View.Views;
import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.Comment;
import com.example.web_banhang.reponsibility.CommentReponsitory;
import com.example.web_banhang.reponsibility.UserReponsitory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class CommentController {
    @Autowired
    private CommentServiceImp commentServiceImp;

    @Autowired
    private UserReponsitory applicationUserRepository;


    @GetMapping("/comment/{idProduct}")
    public ResponseEntity<List<Comment>> getAll(@PathVariable("idProduct") int idProduct) {
        return ResponseEntity.ok(commentServiceImp.getAllComment(idProduct));
    }

    @PostMapping("/comment/add")
    public ResponseEntity<String> createComment(@RequestBody Comment comment, Principal principal) throws JsonProcessingException {
        // Kiểm tra và xử lý newComment
        String content = comment.getContent();

        // Kiểm tra nội dung không được rỗng
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nội dung comment không được trống.");
        }

        // Kiểm tra nội dung không vượt quá giới hạn ký tự
        int maxContentLength = 1000;
        int minContentLength = 1;
        if (content.length() > maxContentLength && content.length()< minContentLength) {
            return ResponseEntity.badRequest().body("Nội dung comment vượt quá giới hạn ký tự cho phép.");
        }

        // Lấy thông tin người dùng hiện tại từ Principal
        String email = principal.getName();
        // Thực hiện truy vấn hoặc xử lý để lấy đối tượng ApplicationUser từ thông tin người dùng (username)
        ApplicationUser user = applicationUserRepository.findByEmail(email).get();

        // Gán thông tin người dùng cho đối tượng Comment
        comment.setUser(user);

        // Lưu comment bằng CommentService
        comment.setCreatedAt(LocalDateTime.now());
        Comment createdComment = commentServiceImp.createComment(comment);

        // Chỉ định view Public cho đối tượng ApplicationUser
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//        String jsonString = objectMapper.writerWithView(Views.Public.class).writeValueAsString(createdComment);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment.toString());
    }

    @PutMapping("/comment/update/{id}")
    public ResponseEntity<String> updateComment(@PathVariable("id") int id, @RequestBody Comment updatedComment, Principal principal) {
        Comment comment = commentServiceImp.getCommentById(id);

        if (comment == null) {
            return ResponseEntity.notFound().build();
        }

        // Kiểm tra xem người dùng hiện tại có quyền cập nhật comment không
        if (!comment.getUser().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Kiểm tra và xử lý updatedComment
        String newContent = updatedComment.getContent();

        // Kiểm tra nội dung mới không được rỗng
        if (newContent == null || newContent.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nội dung comment không được trống.");
        }

        // Kiểm tra nội dung mới có vượt quá giới hạn ký tự không
        int maxContentLength = 1000;
        if (newContent.length() > maxContentLength) {
            return ResponseEntity.badRequest().body("Nội dung comment vượt quá giới hạn ký tự cho phép.");
        }

        // Xử lý các yêu cầu khác với updatedComment (nếu có)

        comment.setContent(newContent);
        // Cập nhật các thuộc tính khác của comment nếu cần
        comment.setUpdatedAt(LocalDateTime.now());
        Comment updateCmt = commentServiceImp.updatwComment(comment);
        return ResponseEntity.ok(updateCmt.toString() + principal.getName());
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") int id, Principal principal) {
        Comment comment = commentServiceImp.getCommentById(id);

        if (comment == null) {
            return ResponseEntity.notFound().build();
        }

        // Kiểm tra xem người dùng hiện tại có quyền xóa comment không
        if (!comment.getUser().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        commentServiceImp.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
