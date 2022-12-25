package com.delivery.app.repository.delivery;

import com.delivery.app.repository.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findDeliveriesByCustomerAndOrderedDateBetween(Account customer, LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    @Query("SELECT d FROM Delivery d WHERE d.id = :id")
    Optional<Delivery> findDeliveryByIdForUpdate(Long id);
}
