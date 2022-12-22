package com.delivery.app.service.account;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JoinData {
    private String accountId;
    private String name;
    private String password;

    @Builder
    public JoinData(String accountId, String name, String password) {
        this.accountId = accountId;
        this.name = name;
        this.password = password;
    }
}
