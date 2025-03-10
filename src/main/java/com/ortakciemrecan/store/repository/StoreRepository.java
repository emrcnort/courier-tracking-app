package com.ortakciemrecan.store.repository;

import com.ortakciemrecan.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String storeName);
}
