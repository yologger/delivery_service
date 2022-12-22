package com.delivery.homework.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("MenuRepository 테스트")
class MenuRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    MenuRepository menuRepository;

    String dummyStoreName = "노들섬 전복집";
    String dummyStoreAddress = "서울 용산구 양녕로 445";
    Store dummyStore;

    Menu dummyMenu;

    @BeforeEach
    void setUp() {
        Store store = Store.builder().name(dummyStoreName).address(dummyStoreAddress).build();
        dummyStore = storeRepository.save(store);

        Menu menu = Menu.builder().name("전복 회").price(50000L).store(store).build();
        dummyMenu = menuRepository.save(menu);
    }

    @Test
    @DisplayName("메뉴 조회 테스트")
    void findMenuByStore() {
        Optional<Menu> _menu = menuRepository.findById(dummyMenu.getId());
        assertThat(_menu.isPresent()).isTrue();
    }
}