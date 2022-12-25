package com.delivery.app.security;

import com.delivery.app.common.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(AuthErrorCode.FORBIDDEN.getStatus());
        response.setContentType("application/json;charset=utf-8");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(AuthErrorCode.FORBIDDEN.getStatus())
                .code(AuthErrorCode.FORBIDDEN.getCode())
                .message(AuthErrorCode.FORBIDDEN.getMessage())
                .build();

        response.getWriter().print(objectMapper.writeValueAsString(errorResponse));
        return;
    }
}
