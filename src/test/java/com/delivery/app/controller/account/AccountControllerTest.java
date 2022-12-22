package com.delivery.app.controller.account;

import com.delivery.app.service.account.AccountAlreadyExistException;
import com.delivery.app.service.account.AccountService;
import com.delivery.app.service.account.JoinResult;
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

/*
 * @WebMvcTest는 Custom SecurityConfig가 아니라 Default SecurityConfig(Auto Configuration)를 사용한다.
 * Default SecurityConfig는 기본적으로
 * 1. 쿠키-세션 기반으로 동작하고
 * 2. CSRF Token 발급이 활성화되어있으며
 * 3. 모든 요청에 대해 인증을 요구한다.
 * 따라서 테스트 환경에서 인증된 사용된 사용자로의 요청을 위해 @WithMockUser을 추가하여 401(Unautorized)를 해결하고
 * CSRF Token을 추가하기 위해 with(csrf())를 호출하여 403(Forbidden)을 해결한다.
 * */
@WebMvcTest
@DisplayName("AccountController 테스트")
class AccountControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountService accountService;

    @Test
    @DisplayName("중복된 id 테스트")
    @WithMockUser
    void duplicate_account_id() throws Exception {

        // Given
        Map<String, String> body = new HashMap<>();
        body.put("account_id", "messi10");
        body.put("name", "Leonel Messi");
        body.put("password", "111aaaAAA@@@");

        when(accountService.join(any()))
                .thenThrow(new AccountAlreadyExistException("Account already exists"));

        // When
        mvc.perform(MockMvcRequestBuilders.post("/account")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)
                        ))
                // Then
                .andExpect(jsonPath("$.code", equalTo("ACCOUNT_JOIN_001")))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Nested
    @DisplayName("id 유효성 테스트")
    class IdValidation {

        @Test
        @DisplayName("유효한 id일 때 성공 테스트")
        @WithMockUser
        void validateId_succeed() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("account_id", "messi10");
            body.put("name", "Leonel Messi");
            body.put("password", "111aaaAAA@@@");

            when(accountService.join(any()))
                    .thenReturn(JoinResult.builder().id(1L).build());

            // When
            mvc.perform(MockMvcRequestBuilders.post("/account")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)
                            ))
                    // Then
                    .andExpect(jsonPath("$.id", equalTo(1)))
                    .andExpect(status().isCreated())
                    .andDo(print());
        }

        @Test
        @DisplayName("잘못된 id일 때 실패 테스트")
        @WithMockUser
        void validateId_fail() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("account_id", "paul");
            body.put("name", "Paul Pogba");
            body.put("password", "111aaaAAA@@@");

            // When
            mvc.perform(MockMvcRequestBuilders.post("/account")
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

    @Nested
    @DisplayName("password 유효성 테스트")
    class PasswordValidation {

        @Test
        @DisplayName("유효한 password일 때 성공 테스트 - 숫자, 소문자, 대문자, 특수문자, 12자리 이상")
        @WithMockUser
        void validatePassword_succeed_1() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("account_id", "messi10");
            body.put("name", "Leonel Messi");
            body.put("password", "111aaaAAA@@@");

            when(accountService.join(any()))
                    .thenReturn(JoinResult.builder().id(1L).build());

            // When
            mvc.perform(MockMvcRequestBuilders.post("/account")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)
                            ))
                    // Then
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", equalTo(1)))
                    .andDo(print());
        }

        @Test
        @DisplayName("유효한 password일 때 성공 테스트 - 소문자, 대문자, 특수문자, 12자리 이상")
        @WithMockUser
        void validatePassword_succeed_2() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("account_id", "messi10");
            body.put("name", "Leonel Messi");
            body.put("password", "aaaaaaAAA@@@");

            when(accountService.join(any()))
                    .thenReturn(JoinResult.builder().id(1L).build());

            // When
            mvc.perform(MockMvcRequestBuilders.post("/account")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(body)
                            ))
                    // Then
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", equalTo(1)))
                    .andDo(print());
        }

        @Test
        @DisplayName("잘못된 password일 때 실패 테스트 - 소문자, 특수문자, 12자리 이하")
        @WithMockUser
        void validatePassword_fail() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("account_id", "messi10");
            body.put("name", "Leonel Messi");
            body.put("password", "aaaa@@@@");

            // When
            mvc.perform(MockMvcRequestBuilders.post("/account")
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