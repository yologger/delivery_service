package com.delivery.app.service.delivery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FindDeliveriesBetweenResult {

    private List<DeliveryData> deliveries;

    @Builder
    public FindDeliveriesBetweenResult(List<DeliveryData> deliveries) {
        this.deliveries = deliveries;
    }
}
