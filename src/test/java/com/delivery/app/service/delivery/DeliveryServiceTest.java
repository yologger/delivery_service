package com.delivery.app.service.delivery;

import com.delivery.app.service.auth.AccountNotExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
@DisplayName("DeliveryService 테스트")
class DeliveryServiceTest {

    @Autowired
    DeliveryService deliveryService;

    @Test
    @DisplayName("기간 내 주문내역 조회 성공 테스트")
    @Transactional
    public void findDeliveriesByPeriod_succeed() {
        // Given
        Long customerId = 1L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime startDateTime = LocalDateTime.parse("2022-12-22 00:00:00.000", formatter);
        LocalDateTime endDateTime = startDateTime.plusDays(3);

        // When
        assertThatNoException().isThrownBy(() -> {
            FindDeliveriesBetweenResult result = deliveryService.findDeliveriesByPeriod(customerId, startDateTime, endDateTime);
            assertThat(result.getDeliveries().size()).isEqualTo(3);
        });
    }

    @Test
    @DisplayName("기간 내 주문내역 조회 실패 테스트 - 기간이 3일을 넘을 때")
    @Transactional
    public void findDeliveriesByPeriod_fail_invalidPeriod() {
        // Given
        Long customerId = 1L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime startDateTime = LocalDateTime.parse("2022-12-22 00:00:00.000", formatter);
        LocalDateTime endDateTime = startDateTime.plusDays(4);

        // When
        assertThatThrownBy(() -> {
            FindDeliveriesBetweenResult result = deliveryService.findDeliveriesByPeriod(customerId, startDateTime, endDateTime);
        }).isInstanceOf(InvalidPeriodException.class);
    }

    @Test
    @DisplayName("기간 내 주문내역 조회 실패 테스트 - 사용자가 존재하지 않을 때")
    @Transactional
    public void findDeliveriesByPeriod_fail_noAccount() {
        // Given
        Long customerId = 0L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime startDateTime = LocalDateTime.parse("2022-12-22 00:00:00.000", formatter);
        LocalDateTime endDateTime = startDateTime.plusDays(3);

        // When
        assertThatThrownBy(() -> {
            FindDeliveriesBetweenResult result = deliveryService.findDeliveriesByPeriod(customerId, startDateTime, endDateTime);
        }).isInstanceOf(AccountNotExistException.class);
    }

    @Test
    @DisplayName("목적지 주소 변경 테스트 성공 - 결제 완료")
    @Transactional
    public void update_destination_succeed_paymentComplete() {

        // Given
        Long deliveryId = 7L;   // status == paymentComplete
        String newAddress = "서울시 동작구 만양로 8길 50 우성아파트";

        assertThatNoException().isThrownBy(() -> {
            UpdateDestinationResult result = deliveryService.updateDestination(deliveryId, newAddress);
            assertThat(result.getNewDestination()).isEqualTo(newAddress);
        });
    }
    @Test
    @DisplayName("목적지 주소 변경 테스트 성공 - 준비 중")
    @Transactional
    public void update_destination_succeed_preparing() {

        // Given
        Long deliveryId = 10L;   // status == preparing
        String newAddress = "서울시 동작구 만양로 8길 50 우성아파트";

        assertThatNoException().isThrownBy(() -> {
            UpdateDestinationResult result = deliveryService.updateDestination(deliveryId, newAddress);
            assertThat(result.getNewDestination()).isEqualTo(newAddress);
        });
    }

    @Test
    @DisplayName("목적지 주소 변경 테스트 실패 - 이미 배송 중일 때")
    @Transactional
    public void update_destination_fail_onDelivery() {

        // Given
        Long deliveryId = 14L;  // status == onDelivery
        String newAddress = "서울시 동작구 만양로 8길 50 우성아파트";

        assertThatThrownBy(() -> {
            UpdateDestinationResult result = deliveryService.updateDestination(deliveryId, newAddress);
            assertThat(result.getNewDestination()).isEqualTo(newAddress);
        }).isInstanceOf(OnDeliveryException.class);
    }

    @Test
    @DisplayName("목적지 주소 변경 테스트 실패 - 배송이 완료되었을 때")
    @Transactional
    public void update_destination_fail_deliveryComplete() {

        // Given
        Long deliveryId = 13L;  // status == deliveryComplete
        String newAddress = "서울시 동작구 만양로 8길 50 우성아파트";

        assertThatThrownBy(() -> {
            UpdateDestinationResult result = deliveryService.updateDestination(deliveryId, newAddress);
            assertThat(result.getNewDestination()).isEqualTo(newAddress);
        }).isInstanceOf(DeliveryCompleteException.class);
    }

    @Test
    @DisplayName("목적지 주소 변경 테스트 실패 - 배달 내역이 존재하지 않을 때")
    @Transactional
    public void update_destination_fail_deliveryNotExist() {

        // Given
        Long deliveryId = 500L;
        String newAddress = "서울시 동작구 만양로 8길 50 우성아파트";

        assertThatThrownBy(() -> {
            UpdateDestinationResult result = deliveryService.updateDestination(deliveryId, newAddress);
            assertThat(result.getNewDestination()).isEqualTo(newAddress);
        }).isInstanceOf(DeliveryNotExistException.class);
    }
}