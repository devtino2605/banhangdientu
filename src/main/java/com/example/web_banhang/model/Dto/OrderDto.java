package com.example.web_banhang.model.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private String fulname;
    private String address;
    private String phoenNumber;
    private int num;
    private double totalMoney;
    private String createAt;
}
