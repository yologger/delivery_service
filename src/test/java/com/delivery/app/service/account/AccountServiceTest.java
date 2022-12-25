package com.delivery.app.service.account;

import com.delivery.app.repository.account.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DisplayName("AccountService 테스트")
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("회원가입 성공 테스트")
    @Transactional
    public void join_succeed() {
        // Given
        String dummyEmail = "dummy1234@gmail.com";
        String dummyName = "dummy";
        String dummyAddress ="address";
        String dummyPassword = "12341234";

        assertThatNoException().isThrownBy(() -> {
            // When
            JoinData data = JoinData.builder()
                    .email(dummyEmail)
                    .name(dummyName)
                    .address(dummyAddress)
                    .password(dummyPassword)
                    .build();

            JoinResult result = accountService.join(data);

            // Then
            assertThat(result.getId()).isPositive();
        });
    }

    @Test
    @DisplayName("회원가입 실패 테스트 - 계정이 이미 존재할 때")
    @Transactional
    public void join_fail_accountAlreadyExists() {

        // Given
        String dummyEmail = "dummy1234@gmail.com";
        String dummyName = "dummy";
        String dummyAddress = "dummy address";
        String dummyPassword = "12341234";
        Account dummyAccount = Account.builder()
                .email(dummyEmail)
                .address(dummyAddress)
                .password(dummyPassword)
                .name(dummyName)
                .build();

        entityManager.persist(dummyAccount);

        // When & Then
        assertThatThrownBy(() -> {
            JoinData data = JoinData.builder()
                    .address(dummyAddress)
                    .email(dummyEmail)
                    .name(dummyName)
                    .password(dummyPassword)
                    .build();

            accountService.join(data);
        }).isInstanceOf(AccountAlreadyExistException.class);
    }
}