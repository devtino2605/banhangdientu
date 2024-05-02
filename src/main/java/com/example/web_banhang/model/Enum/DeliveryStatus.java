package com.example.web_banhang.model.Enum;

public enum DeliveryStatus {
    PENDING("Đang chờ"),
    SHIPPED("Đã gửi"),
    DELIVERED("Đã giao hàng");

    private final String status;

    DeliveryStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
