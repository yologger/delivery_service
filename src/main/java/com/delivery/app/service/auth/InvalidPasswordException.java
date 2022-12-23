package com.delivery.app.service.auth;

import com.delivery.app.common.BusinessException;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
