package com.delivery.app.service.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class DeliveryData {

    @JsonProperty("delivery_id")
    private Long deliveryId;

    @JsonProperty("ordered_at")
    private LocalDateTime orderedAt;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("store_name")
    private String storeName;

    @JsonProperty("menus")
    private List<MenuData> menus;

    @JsonProperty("total_price")
    private Long totalPrice;

    @Builder
    public DeliveryData(Long deliveryId, LocalDateTime orderedAt, String destination, String storeName, List<MenuData> menus, Long totalPrice) {
        this.deliveryId = deliveryId;
        this.orderedAt = orderedAt;
        this.destination = destination;
        this.storeName = storeName;
        this.menus = menus;
        this.totalPrice = totalPrice;
    }
}
