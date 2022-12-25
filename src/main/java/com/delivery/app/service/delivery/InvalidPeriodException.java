package com.delivery.app.service.delivery;

import com.delivery.app.common.BusinessException;

public class InvalidPeriodException extends BusinessException {
    public InvalidPeriodException(String message) {
        super(message);
    }
}
