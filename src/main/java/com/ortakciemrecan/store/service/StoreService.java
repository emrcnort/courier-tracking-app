package com.ortakciemrecan.store.service;

import com.ortakciemrecan.store.entity.Store;
import com.ortakciemrecan.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository repository;
    public Store getStoreByName(String storeName) {
        return repository.findByName(storeName).orElse(null);
    }
}
