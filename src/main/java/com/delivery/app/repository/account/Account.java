package com.delivery.app.repository.account;


import com.delivery.app.repository.delivery.Delivery;
import com.delivery.app.service.account.AuthorityType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String address;

    @Column
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private AuthorityType authority;

    @OneToMany(mappedBy = "customer")
    private List<Delivery> deliveries = new ArrayList<>();

    @Builder
    public Account(String email, String password, String name, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.authority = AuthorityType.USER;
    }

    public void setAuthority(AuthorityType authority) {
        this.authority = authority;
    }
}
