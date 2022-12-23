package com.delivery.app.util;

import com.delivery.app.security.ExpiredAccessTokenException;
import com.delivery.app.security.InvalidAccessTokenException;
import com.delivery.app.security.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@DisplayName("TokenProvider 테스트")
public class TokenProviderTest {

    @InjectMocks
    TokenProvider tokenProvider;

    String secret = "dummy_secret";

    int expireTimeInSeconds = 3;

    @BeforeEach
    public void setUp() {
        // Set up dummy secret, expire
        ReflectionTestUtils.setField(tokenProvider, "secret", secret);
        ReflectionTestUtils.setField(tokenProvider, "expireTimeInSeconds", expireTimeInSeconds);     // 3 seconds
    }

    @Test
    @DisplayName("AccessToken 발행 성공, 검증 성공 테스트")
    public void issueToken_validateToken_succeed() {
        String dummyId = "dummy";
        String dummyPassword = "1234";
        Authentication dummyAuth = new UsernamePasswordAuthenticationToken(dummyId, dummyPassword);
        String accessToken = tokenProvider.issueToken(dummyAuth);

        assertThatNoException().isThrownBy(() -> {
            tokenProvider.validateToken(accessToken);
        });
    }

    @Test
    @DisplayName("AccessToken 검증 실패 테스트 - 잘못된 토큰")
    public void invalid_token() {
        String dummyId = "dummy";
        String dummyPassword = "1234";
        Authentication dummyAuth = new UsernamePasswordAuthenticationToken(dummyId, dummyPassword);
        String accessToken = tokenProvider.issueToken(dummyAuth);

        assertThatThrownBy(() ->
                tokenProvider.validateToken(accessToken + "dummy")
        ).isInstanceOf(InvalidAccessTokenException.class);
    }

    @Test
    @DisplayName("AccessToken 검증 실패 테스트 - 만료된 토큰")
    public void expired_token() throws InterruptedException {
        String dummyId = "dummy";
        String dummyPassword = "1234";
        Authentication dummyAuth = new UsernamePasswordAuthenticationToken(dummyId, dummyPassword);
        String accessToken = tokenProvider.issueToken(dummyAuth);

        Thread.sleep((expireTimeInSeconds+1) * 1000);

        assertThatThrownBy(() ->
                tokenProvider.validateToken(accessToken)
        ).isInstanceOf(ExpiredAccessTokenException.class);
    }
}
