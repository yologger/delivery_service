package com.delivery.app.controller.account;

import com.delivery.app.security.TokenProvider;
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

@WebMvcTest(controllers = AccountController.class)
@DisplayName("AccountController 입력값 검증 테스트")
class AccountControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountService accountService;

    @MockBean
    TokenProvider tokenProvider;

    @Nested
    @DisplayName("email 유효성 테스트")
    class IdValidation {

        @Test
        @DisplayName("중복된 email 테스트")
        @WithMockUser
        void duplicate_email() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("email", "messi10@gmail.com");
            body.put("name", "Leonel Messi");
            body.put("address", "dummy address");
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

        @Test
        @DisplayName("유효한 email일 때 성공 테스트")
        @WithMockUser
        void validateEmail_succeed() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("email", "messi10@gmail.com");
            body.put("name", "Leonel Messi");
            body.put("address", "dummy address");
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
        @DisplayName("유효하지 않은 email일 때 실패 테스트")
        @WithMockUser
        void validateEmail_fail() throws Exception {

            // Given
            Map<String, String> body = new HashMap<>();
            body.put("email", "paul");
            body.put("name", "Paul Pogba");
            body.put("address", "dummy address");
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
            body.put("email", "messi10@gmail.com");
            body.put("name", "Leonel Messi");
            body.put("address", "dummy address");
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
            body.put("email", "messi10@gmail.com");
            body.put("name", "Leonel Messi");
            body.put("address", "dummy address");
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
            body.put("email", "messi10@gmail.com");
            body.put("name", "Leonel Messi");
            body.put("address", "dummy address");
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