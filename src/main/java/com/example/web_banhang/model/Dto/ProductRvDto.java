package com.example.web_banhang.model.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRvDto {
    private int id;
    private String productName;
    private double rating;
    private String userEmail;
}
