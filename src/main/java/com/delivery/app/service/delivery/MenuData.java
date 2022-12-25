package com.delivery.app.service.delivery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuData {

    private String name;
    private int count;
    private Long price;

    @Builder
    public MenuData(String name, int count, Long price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }
}
