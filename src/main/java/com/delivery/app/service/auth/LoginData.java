package com.delivery.app.service.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginData {
    private String email;
    private String password;

    @Builder
    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
