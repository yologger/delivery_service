package com.delivery.app.service.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DisplayName("AuthService 테스트")
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    @DisplayName("로그인 성공 테스트")
    @Transactional
    public void login_succeed() {
        // Given
        String email =  "tester1@gmail.com";
        String password = "aaaAAA111!!!";
        LoginData data = LoginData.builder()
                .email(email)
                .password(password)
                .build();

        // When & Then
        assertThatNoException().isThrownBy(() -> {
            LoginResult result = authService.login(data);
            assertThat(result.getAccessToken()).isNotBlank();
        });
    }

    @Test
    @DisplayName("로그인 실패 테스트 - 계정이 존재하지 않을 때")
    @Transactional
    public void login_fail_wrong_id() {
        // Given
        String email = "tester0@gmail.com";
        String password = "aaaAAA111!!!";
        LoginData data = LoginData.builder()
                .email(email)
                .password(password)
                .build();

        // When & Then
        assertThatThrownBy(() -> {
            LoginResult result = authService.login(data);
        }).isInstanceOf(AccountNotExistException.class);
    }

    @Test
    @DisplayName("로그인 실패 테스트 - 비밀번호가 틀렸을 때")
    @Transactional
    public void login_fail_wrong_password() {
        // Given
        String email = "tester1@gmail.com";
        String password = "aaaAAA111!!!99";
        LoginData data = LoginData.builder()
                .email(email)
                .password(password)
                .build();

        // When & Then
        assertThatThrownBy(() -> {
            LoginResult result = authService.login(data);
        }).isInstanceOf(InvalidPasswordException.class);
    }
}