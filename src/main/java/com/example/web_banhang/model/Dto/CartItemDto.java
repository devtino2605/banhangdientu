package com.example.web_banhang.model.Dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private ProductDto productDto;
    private int id;
    private int quantity;
    private double price;
    private String username;
}
