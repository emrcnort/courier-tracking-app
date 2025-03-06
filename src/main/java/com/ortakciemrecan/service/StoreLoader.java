package com.ortakciemrecan.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ortakciemrecan.dto.Store;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StoreLoader {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String STORE_KEY = "stores";
    @PostConstruct
    public void loadStores() {
        List<Store> stores = loadFromJson();
        redisTemplate.opsForValue().set(STORE_KEY, stores);
    }
    public List<Store> getStores() {
        return (List<Store>) redisTemplate.opsForValue().get(STORE_KEY);
    }


    private List<Store> loadFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try  {
            File file = Paths.get("stores.json").toFile();
            return objectMapper.readValue(file, new TypeReference<List<Store>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

