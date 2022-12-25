package com.delivery.app.repository.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("AccountRepository 테스트")
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    String dummyEmail = "dummyId@gmail.com";
    String dummyPassword = "dummyPassword";
    String dummyName = "dummyName";

    String dummyAddress ="dummyAddress";
    Account dummyAccount;

    @BeforeEach
    void setUp() {
        Account account = Account.builder().email(dummyEmail).name(dummyName).address(dummyAddress).password(dummyPassword).build();
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
        boolean exists = accountRepository.existsByEmail(dummyEmail);

        // Then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("account_id로 계정 조회 테스트")
    void findByAccountId() {
        // When
        Optional<Account> _account = accountRepository.findByEmail("tester1@gmail.com");

        // Then
        assertThat(_account.isPresent()).isTrue();
        assertThat(_account.get().getName()).isEqualTo("tester1");
    }
}