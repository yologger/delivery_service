package com.delivery.app.repository.delivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("DeliveryDetailRepository 테스트")
class DeliveryDetailRepositoryTest {

    @Autowired DeliveryDetailRepository deliveryDetailRepository;

    @Test
    @DisplayName("모든 배달 상세 내역 조회")
    void findAll() {
        // When
        List<DeliveryDetail> deliveryDetails = deliveryDetailRepository.findAll();

        // Then
        assertThat(deliveryDetails.size()).isPositive();
    }
}