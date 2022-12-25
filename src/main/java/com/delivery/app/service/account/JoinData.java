package com.delivery.app.service.account;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JoinData {
    private String email;
    private String name;
    private String address;
    private String password;

    @Builder
    public JoinData(String email, String name, String address, String password) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.password = password;
    }
}
