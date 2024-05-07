package com.example.web_banhang.Services.Imp;

import com.example.web_banhang.Services.OrderService;
import com.example.web_banhang.mapper.OrderMapper;
import com.example.web_banhang.model.Dto.OrderDto;
import com.example.web_banhang.model.Enum.DeliveryStatus;
import com.example.web_banhang.model.OrderDetails;
import com.example.web_banhang.model.Orders;
import com.example.web_banhang.reponsibility.OrderDetailReponsitory;
import com.example.web_banhang.reponsibility.OrderReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private final OrderReponsitory orderRepository;
    @Autowired
    private final OrderDetailReponsitory orderDetailRepository;

    public OrderServiceImp(OrderReponsitory orderRepository, OrderDetailReponsitory orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }
    @Override
    public Orders createOrderWithDetails(Orders order, List<OrderDetails> orderDetails) {
        // Lưu đơn hàng vào cơ sở dữ liệu
        Orders savedOrder = orderRepository.save(order);

        // Thêm các OrderDetail và tính toán num và total_money
        int num = 0;
        double totalMoney = 0.0;
        for (OrderDetails orderDetail : orderDetails) {
            orderDetail.setOrder(savedOrder);
            orderDetailRepository.save(orderDetail);

            // Tính toán num
            num += orderDetail.getQuantity();

            // Tính toán total_money
            totalMoney += orderDetail.getPrice() * orderDetail.getQuantity();
        }

        // Cập nhật giá trị num và total_money của đơn hàng
        DeliveryStatus status = DeliveryStatus.PENDING;
        int value = status.getStatus();

        savedOrder.setStatus(value);
        savedOrder.setNum(num);
        savedOrder.setTotalMoney(totalMoney);
        orderRepository.save(savedOrder);

        return savedOrder;
    }
}
