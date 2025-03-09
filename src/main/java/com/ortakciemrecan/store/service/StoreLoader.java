package com.ortakciemrecan.store.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ortakciemrecan.store.dto.StoreDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StoreLoader {
    private final StoreService storeService;
    @PostConstruct
    public void loadStores() {
        List<StoreDto> stores = loadFromJson();
        storeService.saveAll(stores);
    }

    private List<StoreDto> loadFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try  {
            File file = Paths.get("stores.json").toFile();
            return objectMapper.readValue(file, new TypeReference<List<StoreDto>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

