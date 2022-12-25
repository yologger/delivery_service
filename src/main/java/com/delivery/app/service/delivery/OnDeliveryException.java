package com.delivery.app.service.delivery;

import com.delivery.app.common.BusinessException;

public class OnDeliveryException extends BusinessException {
    public OnDeliveryException(String message) {
        super(message);
    }
}
