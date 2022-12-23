package com.delivery.app.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("PasswordEncoder 테스트")
public class PasswordEncoderTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("암호화 및 비교 테스트")
    void encode_decode() {

        // Given
        String rawPassword = "aaaAAA111!!!";

        String encodedPassword = passwordEncoder.encode(rawPassword);

        // When & Then
        assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
    }
}