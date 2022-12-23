package com.delivery.app.security;

import com.delivery.app.common.BusinessException;

public class InvalidAccessTokenException extends BusinessException {

    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
