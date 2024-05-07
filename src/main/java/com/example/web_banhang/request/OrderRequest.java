package com.example.web_banhang.request;

import com.example.web_banhang.model.OrderDetails;
import com.example.web_banhang.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Orders order;
    private List<OrderDetails> orderDetails;
}
