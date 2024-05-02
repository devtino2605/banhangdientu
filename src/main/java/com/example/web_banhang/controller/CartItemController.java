package com.example.web_banhang.controller;

import com.example.web_banhang.Services.Imp.CartItemServiceImp;
import com.example.web_banhang.mapper.ProductMapper;
import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.CartItem;
import com.example.web_banhang.model.Dto.CartItemDto;
import com.example.web_banhang.model.Product;
import com.example.web_banhang.reponsibility.ProductReponsitory;
import com.example.web_banhang.reponsibility.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class CartItemController {
    @Autowired
    private CartItemServiceImp cartItemServiceImp;
    @Autowired
    private UserReponsitory userReponsitory;
    @Autowired
    private ProductReponsitory productReponsitory;

    @GetMapping("/cartitem")
    public ResponseEntity<List<CartItem>> getAllByUser(Principal principal){
        List<CartItem> list = cartItemServiceImp.getAllItemsByUser(userReponsitory.findByUsername(principal.getName()));
        return ResponseEntity.ok(list);
    }

    @PostMapping("/cartitem/addItem")
    public ResponseEntity<CartItemDto> addItem(@Valid  @RequestBody CartItem cartItem, Principal principal, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            // Xử lý thông tin lỗi ở đây
            return ResponseEntity.badRequest().build();
        }
        Product product = null;
        if(productReponsitory.findById(cartItem.getProduct().getId()).isPresent()){
            product = productReponsitory.findById(cartItem.getProduct().getId()).get();
        }
        cartItem.setProduct(product);
        assert product != null;
        cartItem.setPrice(product.getPrice()*cartItem.getQuantity());

        String email = principal.getName();
        ApplicationUser user = userReponsitory.findByEmail(email).get();

        // Gán thông tin người dùng cho đối tượng Comment
        cartItem.setUser(user);
        CartItemDto saveItem = cartItemServiceImp.addItem(cartItem);
        return ResponseEntity.ok(saveItem);
    }
    @DeleteMapping("/cartitem/delete/item={id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") int id, Principal principal){
        CartItem item = cartItemServiceImp.getItems(id).get();
        if (!item.getUser().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        cartItemServiceImp.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
