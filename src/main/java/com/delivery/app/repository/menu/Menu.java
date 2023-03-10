package com.delivery.app.repository.menu;

import com.delivery.app.repository.store.Store;
import com.delivery.app.service.account.AuthorityType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;

    @ManyToOne
    private Store store;

    @Builder
    public Menu(String name, Long price, Store store) {
        this.name = name;
        this.price = price;
        setStore(store);
    }

    public void setStore(Store store) {
        if (this.store != null) {
            this.store.getMenus().remove(this);
        }
        this.store = store;
        store.getMenus().add(this);
    }
}
