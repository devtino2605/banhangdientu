package com.example.web_banhang.Services;

import com.example.web_banhang.model.ApplicationUser;
import com.example.web_banhang.model.CartItem;
import com.example.web_banhang.model.Dto.CartItemDto;

import java.util.List;
import java.util.Optional;

public interface CartItemService {
    List<CartItem> getAllItemsByUser(ApplicationUser user);

    CartItemDto addItem(CartItem item);
    void deleteItem(int id);

    Optional<CartItem> getItems(int id);
}
