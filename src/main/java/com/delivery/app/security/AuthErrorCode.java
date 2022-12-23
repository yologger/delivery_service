package com.delivery.app.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCode {

    NO_AUTHORIZATION_HEADER(401, "AUTH_001", "No Authorization header"),
    NO_BEARER(401, "AUTH_002", "Authorization header must start with bearer"),
    INVALID_TOKEN(401, "AUTH_003", "Invalid token"),
    EXPIRED_TOKEN(401, "AUTH_004", "Expired token"),
    UNKNOWN_SERVER_ERROR(500, "AUTH_005", "Unknown server error"),
    FORBIDDEN(500, "AUTH_006", "forbidden"),
    ACCOUNT_NOT_EXIST(400, "AUTH_007", "Account not exist"),
    INVALID_PASSWORD(401, "AUTH_008", "Invalid password")
    ;

    private final int status;
    private final String code;
    private final String message;
}
