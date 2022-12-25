package com.delivery.app.service.account;

public enum AuthorityType {
    USER("USER"),
    ADMIN("ADMIN");

    private String description;

    AuthorityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
