package com.delivery.app.repository.account;


import com.delivery.app.service.account.AuthorityType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accountId;

    @Column
    private String password;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private AuthorityType authority;

    @Builder
    public Account(String accountId, String password, String name) {
        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.authority = AuthorityType.USER;
    }

    public void setAuthority(AuthorityType authority) {
        this.authority = authority;
    }
}
