package com.example.web_banhang.model.Enum;

import lombok.Getter;


@Getter
public enum DeliveryStatus {
    PENDING(1),
    ON_THE_WAY(2),
    DELIVERED(3),
    CANCELLED(4);

    private final int status;

    DeliveryStatus(int status) {
        this.status = status;
    }

}
