package com.example.web_banhang.model.Enum;

public enum PaymentMethod {
    CASH_ON_DELIVERY("Thanh toán khi nhận hàng"),
    BANK_TRANSFER("Chuyển khoản ngân hàng"),
    MMO("MMO");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
