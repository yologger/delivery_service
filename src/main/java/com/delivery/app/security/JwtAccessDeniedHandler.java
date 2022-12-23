package com.delivery.app.security;

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

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(AuthErrorCode.FORBIDDEN.getStatus());
        response.setContentType("application/json;charset=utf-8");
        Map body = new HashMap<>();
        body.put("status", AuthErrorCode.FORBIDDEN.getStatus());
        body.put("code", AuthErrorCode.FORBIDDEN.getCode());
        body.put("message", AuthErrorCode.FORBIDDEN.getMessage());
        response.getWriter().print(body);
        return;
    }
}
