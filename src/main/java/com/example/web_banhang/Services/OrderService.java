package com.example.web_banhang.Services;

import com.example.web_banhang.model.Dto.OrderDto;
import com.example.web_banhang.model.OrderDetails;
import com.example.web_banhang.model.Orders;

import java.util.List;

public interface OrderService {
    public Orders createOrderWithDetails(Orders order, List<OrderDetails> orderDetails);
}
