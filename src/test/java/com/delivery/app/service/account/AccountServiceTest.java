package com.delivery.app.service.account;


import com.delivery.app.repository.account.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

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
        String dummyAccountId = "dummy1234";
        String dummyName = "dummy";
        String dummyPassword = "12341234";

        try {
            // When
            JoinData data = JoinData.builder()
                    .accountId(dummyAccountId)
                    .name(dummyName)
                    .password(dummyPassword)
                    .build();

            JoinResult result = accountService.join(data);

            // Then
            assertThat(result.getId()).isPositive();
        } catch (AccountAlreadyExistException e) {
            fail();
        }
    }

    @Test
    @DisplayName("회원가입 실패 테스트 - 계정이 이미 존재할 때")
    @Transactional
    public void join_fail_accountAlreadyExists() {

        // Given
        String dummyAccountId = "dummy1234";
        String dummyName = "dummy";
        String dummyPassword = "12341234";
        Account dummyAccount = Account.builder()
                .accountId(dummyAccountId)
                .password(dummyPassword)
                .name(dummyName)
                .build();

        entityManager.persist(dummyAccount);

        // When & Then
        assertThatThrownBy(() -> {
            JoinData data = JoinData.builder()
                    .accountId(dummyAccountId)
                    .name(dummyName)
                    .password(dummyPassword)
                    .build();

            accountService.join(data);
        }).isInstanceOf(AccountAlreadyExistException.class);
    }
}