package com.delivery.app.repository.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("AccountRepository 테스트")
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    String dummyAccountId = "dummyId";
    String dummyPassword = "dummyPassword";
    String dummyName = "dummyName";
    Account dummyAccount;

    @BeforeEach
    void setUp() {
        Account account = Account.builder().accountId(dummyAccountId).name(dummyName).password(dummyPassword).build();
        dummyAccount = accountRepository.save(account);
    }

    @Test
    @DisplayName("모든 계정 조회 테스트")
    void findAll() {
        // When
        List<Account> accountList = accountRepository.findAll();

        // Then
        assertThat(accountList.size()).isNotZero();
    }

    @Test
    @DisplayName("계정 존재 여부 테스트")
    void existsByAccountId() {
        // When
        boolean exists = accountRepository.existsByAccountId(dummyAccountId);

        // Then
        assertThat(exists).isTrue();
    }
}