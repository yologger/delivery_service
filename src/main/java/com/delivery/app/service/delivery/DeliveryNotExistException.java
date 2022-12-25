package com.delivery.app.service.delivery;

import com.delivery.app.common.BusinessException;

public class DeliveryNotExistException extends BusinessException {
    public DeliveryNotExistException(String message) {
        super(message);
    }
}
