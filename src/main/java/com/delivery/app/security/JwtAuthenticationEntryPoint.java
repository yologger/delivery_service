package com.delivery.app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        String exception = (String)request.getAttribute("exception");
        AuthErrorCode errorCode = null;

        if (exception == null) {
            errorCode = AuthErrorCode.UNKNOWN_SERVER_ERROR;
            sendErrorResponse(response, errorCode);
            return;
        }

        // No authorization header
        if (exception.equals(AuthErrorCode.NO_AUTHORIZATION_HEADER.getCode())) {
            errorCode = AuthErrorCode.NO_AUTHORIZATION_HEADER;
            sendErrorResponse(response, errorCode);
            return;
        }

        // No start with bearer
        if (exception.equals(AuthErrorCode.NO_BEARER.getCode())) {
            errorCode = AuthErrorCode.NO_BEARER;
            sendErrorResponse(response, errorCode);
            return;
        }

        // Expired token
        if (exception.equals(AuthErrorCode.EXPIRED_TOKEN.getCode())) {
            errorCode = AuthErrorCode.EXPIRED_TOKEN;
            sendErrorResponse(response, errorCode);
            return;
        }

        // Invalid token
        if (exception.equals(AuthErrorCode.INVALID_TOKEN.getCode())) {
            errorCode = AuthErrorCode.INVALID_TOKEN;
            sendErrorResponse(response, errorCode);
            return;
        }
    }

    private void sendErrorResponse(HttpServletResponse response, AuthErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus());
        response.setContentType("application/json;charset=utf-8");
        Map body = new HashMap<>();
        body.put("status", errorCode.getStatus());
        body.put("code", errorCode.getCode());
        body.put("message", errorCode.getMessage());
        response.getWriter().print(body);
        return;
    }
}
