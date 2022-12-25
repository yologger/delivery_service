package com.delivery.app.repository.store;

import com.delivery.app.repository.delivery.Delivery;
import com.delivery.app.repository.menu.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @OneToMany(mappedBy = "store")
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Delivery> deliveries = new ArrayList<>();

    @Builder
    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
