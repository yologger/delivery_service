package com.delivery.app.service.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginData {
    private String accountId;
    private String password;

    @Builder
    public LoginData(String accountId, String password) {
        this.accountId = accountId;
        this.password = password;
    }
}
