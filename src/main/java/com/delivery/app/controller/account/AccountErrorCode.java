package com.delivery.app.controller.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountErrorCode {
    ACCOUNT_ALREADY_EXIST(400, "ACCOUNT_JOIN_001", "Account already exists")
    ;

    private final int status;
    private final String code;
    private final String message;
}
