package com.delivery.app.service.account;

import com.delivery.app.common.BusinessException;

public class AccountAlreadyExistException extends BusinessException {
    public AccountAlreadyExistException(String message) { super(message); }
}
