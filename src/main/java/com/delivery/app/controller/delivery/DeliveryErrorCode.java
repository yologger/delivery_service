package com.delivery.app.controller.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryErrorCode {
    ACCOUNT_NOT_EXIST(400, "DELIVERY_001", "Account not exists"),
    INVALID_PERIOD(400, "DELIVERY_002", "Period must be less than or equal to 3 days"),
    DELIVERY_NOT_EXIST(400, "DELIVERY_003", "Delivery not exist."),
    ON_DELIVERY(400, "DELIVERY_004", "Delivery status is 'on delivery'"),
    DELIVERY_COMPLETE(400, "DELIVERY_005", "Delivery status is 'delivery complete'")
    ;

    private final int status;
    private final String code;
    private final String message;
}
