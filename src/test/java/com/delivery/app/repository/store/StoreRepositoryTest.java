package com.delivery.app.repository.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("StoreRepository 테스트")
class StoreRepositoryTest {
    @Autowired
    StoreRepository storeRepository;

    String dummyStoreName = "제주 흑돼지집";
    String dummyStoreAddress = "제주특별자치도 서귀포시 성산읍 일출로 267";
    Store dummyStore;

    @BeforeEach
    void setUp() {
        Store store = Store.builder().name(dummyStoreName).address(dummyStoreAddress).build();
        dummyStore = storeRepository.save(store);
    }

    @Test
    @DisplayName("가게 전체 조회 테스트")
    void findAll() {
        // When
        List<Store> stores = storeRepository.findAll();

        // Then
        assertThat(stores.size()).isNotZero();
    }

    @Test
    @DisplayName("id로 가게 조회 테스트")
    void findById() {
        // When
        Optional<Store> _store = storeRepository.findById(dummyStore.getId());

        // Then
        assertThat(_store.isPresent()).isTrue();
        assertThat(_store.get().getName()).isEqualTo(dummyStoreName);
        assertThat(_store.get().getAddress()).isEqualTo(dummyStoreAddress);
    }
}