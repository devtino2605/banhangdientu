package com.example.web_banhang.controller;

import com.example.web_banhang.Services.Imp.OrderServiceImp;
import com.example.web_banhang.Services.OrderService;
import com.example.web_banhang.model.Orders;
import com.example.web_banhang.reponsibility.UserReponsitory;
import com.example.web_banhang.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrderServiceImp orderService;

    @Autowired
    private UserReponsitory userReponsitory;

    public OrderController(OrderServiceImp orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest orderRequest, Principal principal) {

        Orders setOrder = orderRequest.getOrder();
        setOrder.setUser(userReponsitory.findByUsername(principal.getName()));
        Orders order = orderService.createOrderWithDetails(setOrder, orderRequest.getOrderDetails());
        return ResponseEntity.ok(order);
    }
}
