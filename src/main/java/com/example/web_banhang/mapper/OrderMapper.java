package com.example.web_banhang.mapper;

import com.example.web_banhang.model.Dto.OrderDto;
import com.example.web_banhang.model.Orders;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

public class OrderMapper {
    public static OrderDto mapToDto(Orders order){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String current = (order.getOrderDate()).format(formatter);
        return new OrderDto(
                order.getId(),
                order.getUser().getFulname(),
                order.getAddress(),
                order.getPhoneNumber(),
                order.getNum(),
                order.getTotalMoney(),
                current
        );
    }
}
