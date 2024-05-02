package com.example.web_banhang.mapper;

import com.example.web_banhang.model.CartItem;
import com.example.web_banhang.model.Dto.CartItemDto;

public class CartItemMapper {
    public static CartItemDto mapToDto(CartItem item){
        return new CartItemDto(
                ProductMapper.maptoDto(item.getProduct()),
                item.getId(),
                item.getQuantity(),
                item.getPrice(),
                item.getUser().getUsername()
        );
    }
}
