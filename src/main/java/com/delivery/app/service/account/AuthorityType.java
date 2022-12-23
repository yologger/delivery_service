package com.delivery.app.service.account;

public enum AuthorityType {

    ADMIN("ADMIN"),
    USER("USER");

    private String description;

    AuthorityType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
