package com.delivery.app.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    final private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        String requestURI = request.getRequestURI();

        try {
            if (StringUtils.hasText(authorizationHeader)) {
                if (authorizationHeader.startsWith("Bearer ")) {
                    String jwt = authorizationHeader.substring(7);

                    // Validate access token
                    tokenProvider.validateToken(jwt);

                    // Set up Authentication in SecurityContext
                    Authentication authentication = tokenProvider.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("[JwtFilter] VALID ACCESS TOKEN, Authorization Info has been saved in 'SecurityContext' (URL: " + requestURI + ")");
                } else {
                    // Authorization header not start with 'bearer'.
                    log.info("[JwtFilter] Authorization header not start with 'bearer");
                    request.setAttribute("exception", AuthErrorCode.NO_BEARER.getCode());
                }
            } else {
                // No Authorization header.
                log.info("[JwtFilter] No authorization token");
                request.setAttribute("exception", AuthErrorCode.NO_AUTHORIZATION_HEADER.getCode());
            }
        } catch (ExpiredAccessTokenException e) {
            // Expired Token
            log.info("[JwtFilter] Expired token");
            request.setAttribute("exception", AuthErrorCode.EXPIRED_TOKEN.getCode());
        } catch (InvalidAccessTokenException e) {
            // Invalid Token
            log.info("[JwtFilter] Invalid token");
            request.setAttribute("exception", AuthErrorCode.INVALID_TOKEN.getCode());
        }
        filterChain.doFilter(request, response);
    }
}
