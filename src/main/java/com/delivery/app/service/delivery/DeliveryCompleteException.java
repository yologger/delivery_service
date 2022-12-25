package com.delivery.app.service.delivery;

import com.delivery.app.common.BusinessException;

public class DeliveryCompleteException extends BusinessException {
    public DeliveryCompleteException(String message) {
        super(message);
    }
}
