package com.delivery.app.repository.delivery;

import com.delivery.app.repository.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findDeliveriesByCustomerAndOrderedDateBetween(Account customer, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
