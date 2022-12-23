package com.delivery.app.security;

import com.delivery.app.common.BusinessException;

public class ExpiredAccessTokenException extends BusinessException {
    public ExpiredAccessTokenException(String message) {
        super(message);
    }
}
