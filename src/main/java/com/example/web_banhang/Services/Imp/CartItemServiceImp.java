package com.example.web_banhang.Services.Imp;

import com.example.web_banhang.Services.CartItemService;
import com.example.web_banhang.mapper.CartItemMapper;
import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.CartItem;
import com.example.web_banhang.model.Dto.CartItemDto;
import com.example.web_banhang.reponsibility.CartItemReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImp implements CartItemService {
    @Autowired
    private CartItemReponsitory cartIermReponsitory;

    @Override
    public List<CartItem> getAllItemsByUser(ApplicationUser user) {
        return cartIermReponsitory.findAllByUser(user);
    }

    @Override
    public CartItemDto addItem(CartItem item) {
        CartItem saveItem = cartIermReponsitory.save(item);
        return CartItemMapper.mapToDto(saveItem);
    }

    @Override
    public void deleteItem(int id) {
        cartIermReponsitory.deleteById(id);
    }

    @Override
    public Optional<CartItem> getItems(int id) {
        return cartIermReponsitory.findById(id);
    }
}
