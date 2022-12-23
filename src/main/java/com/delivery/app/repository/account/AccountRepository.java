package com.delivery.app.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByAccountId(String accountId);

    Optional<Account> findByAccountId(String accountId);
}
