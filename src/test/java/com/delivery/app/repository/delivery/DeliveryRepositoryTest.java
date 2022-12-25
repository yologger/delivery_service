package com.delivery.app.repository.delivery;

import com.delivery.app.repository.account.Account;
import com.delivery.app.repository.account.AccountRepository;
import com.delivery.app.service.auth.AccountNotExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DataJpaTest
@DisplayName("DeliveryRepository 테스트")
class DeliveryRepositoryTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    DeliveryRepository deliveryRepository;

    @Test
    @DisplayName("모든 배달 내역 조회")
    void findAll() {
        // When
        List<Delivery> deliveryList = deliveryRepository.findAll();

        // Then
        assertThat(deliveryList.size()).isNotZero();
    }

    @Test
    @DisplayName("날짜로 배달 내역 조회 테스트")
    void findDeliveriesByOrderedDate() {
        Long dummyId = 1L;
        assertThatNoException().isThrownBy(() -> {
            Account customer = accountRepository.findById(dummyId)
                    .orElseThrow(() -> new AccountNotExistException("Account not exists."));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime startDateTime = LocalDateTime.parse("2022-12-22 00:00:00.000", formatter);
            LocalDateTime endDateTime = startDateTime.plusDays(3);
            List<Delivery> deliveries = deliveryRepository.findDeliveriesByCustomerAndOrderedDateBetween(customer, startDateTime, endDateTime);
            assertThat(deliveries.size()).isEqualTo(3);
        });
    }
}