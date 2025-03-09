package com.ortakciemrecan.store.service;

import com.ortakciemrecan.common.exception.StoreNotExistException;
import com.ortakciemrecan.store.dto.StoreDto;
import com.ortakciemrecan.store.entity.Store;
import com.ortakciemrecan.store.repository.StoreRepository;
import com.ortakciemrecan.store.util.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository repository;
    private final StoreMapper mapper;
    public Store getStoreByName(String storeName) throws StoreNotExistException {
        return repository.findByName(storeName).orElseThrow(StoreNotExistException::new);
    }

    public List<StoreDto> getStores() {
        return mapper.convertEntityListToDtoList(repository.findAll());
    }

    public void saveAll(List<StoreDto> stores) {
        repository.saveAllAndFlush(mapper.convertDtoListToEntityList(stores));
    }
}
