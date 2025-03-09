package com.ortakciemrecan.store.util;

import com.ortakciemrecan.common.utils.BaseMapper;
import com.ortakciemrecan.store.dto.StoreDto;
import com.ortakciemrecan.store.entity.Store;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StoreMapper extends BaseMapper<Store, StoreDto> {
}
