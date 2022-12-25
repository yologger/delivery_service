package com.delivery.app.controller.auth;

import com.delivery.app.security.TokenProvider;
import com.delivery.app.service.account.AccountAlreadyExistException;
import com.delivery.app.service.account.JoinResult;
import com.delivery.app.service.auth.AuthService;
import com.delivery.app.service.auth.LoginResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = AuthController.class)
@DisplayName("AuthControllerTest 입력값 검증 테스트")
class AuthControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AuthService authService;

    @MockBean
    TokenProvider tokenProvider;


    @Nested
    @DisplayName("email 유효성 테스트")
    class IdValidation {

        @Test
        @DisplayName("유효한 email일 때 성공 테스트")
        @WithMockUser
        void validateEmail_succeed() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("email", "messi10@gmail.com");
            body.put("password", "111aaaAAA@@@");

            String dummyAccessToken = "dummy_access_token";

            when(authService.login(any()))
                    .thenReturn(LoginResult.builder().accessToken(dummyAccessToken).build());

            // When
            mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)
                            ))
                    // Then
                    .andExpect(jsonPath("$.access_token", equalTo(dummyAccessToken)))
                    .andExpect(status().isCreated())
                    .andDo(print());
        }

        @Test
        @DisplayName("유효하지 않은 email일 때 실패 테스트")
        @WithMockUser
        void validateEmail_fail() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("email", "paul");
            body.put("name", "Paul Pogba");
            body.put("password", "111aaaAAA@@@");

            // When
            mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)
                            ))
                    // Then
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code", equalTo("GLOBAL_001")))
                    .andDo(print());
        }
    }

}