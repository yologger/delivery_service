package com.delivery.app.service.auth;

import com.delivery.app.repository.account.Account;
import com.delivery.app.repository.account.AccountRepository;
import com.delivery.app.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final AccountRepository accountRepository;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResult login(LoginData data) throws AccountNotExistException, InvalidPasswordException {

        Account account = accountRepository.findByAccountId(data.getAccountId())
                .orElseThrow(() -> new AccountNotExistException("Account not found"));

        if (!passwordEncoder.matches(data.getPassword(), account.getPassword()))
            throw new InvalidPasswordException("Invalid password");

        // 인증 데이터 생성
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getAccountId(), data.getPassword()));

        // 인증정보 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 토큰 발급
        String accessToken = tokenProvider.issueToken(authentication);

        return LoginResult.builder().accessToken(accessToken).build();
    }
}
