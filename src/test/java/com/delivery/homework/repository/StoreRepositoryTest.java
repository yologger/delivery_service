package com.delivery.homework.repository;

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

    String dummyStoreName = "노들섬 전복내장탕";
    String dummyStoreAddress = "서울 용산구 양녕로 445";
    Store dummyStore;

    @BeforeEach
    void setUp() {
        dummyStore = storeRepository.save(Store.builder().name(dummyStoreName).address(dummyStoreAddress).build());
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