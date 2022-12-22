package com.delivery.homework.repository;

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

    @Autowired AccountRepository accountRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("모든 계정 조회 테스트")
    void findAll() {
        // When
        List<Account> accountList = accountRepository.findAll();

        // Then
        assertThat(accountList.size()).isNotZero();
    }
}