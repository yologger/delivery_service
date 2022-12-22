package com.delivery.app.service.account;

import com.delivery.app.repository.account.Account;
import com.delivery.app.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    final private AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public JoinResult join(JoinData data) throws AccountAlreadyExistException {
        if (accountRepository.existsByAccountId(data.getAccountId())) throw new AccountAlreadyExistException("Account already exists");
        Account newAccount = Account.builder()
                .accountId(data.getAccountId())
                .name(data.getName())
                .password(passwordEncoder.encode(data.getPassword()))
                .build();
        Account created = accountRepository.save(newAccount);
        return JoinResult.builder().id(created.getId()).build();
    }
}
