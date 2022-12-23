package com.delivery.app.service.auth;

import com.delivery.app.common.BusinessException;

public class AccountNotExistException extends BusinessException {
    public AccountNotExistException(String message) {
        super(message);
    }
}
