package com.ortakciemrecan.store.util;

import com.ortakciemrecan.store.dto.StoreDto;
import com.ortakciemrecan.store.entity.Store;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-09T17:36:40+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class StoreMapperImpl implements StoreMapper {

    @Override
    public StoreDto convertEntityToDto(Store entity) {
        if ( entity == null ) {
            return null;
        }

        StoreDto storeDto = new StoreDto();

        storeDto.setName( entity.getName() );
        storeDto.setLatitude( entity.getLatitude() );
        storeDto.setLongitude( entity.getLongitude() );

        return storeDto;
    }

    @Override
    public Store convertDtoToEntity(StoreDto dto) {
        if ( dto == null ) {
            return null;
        }

        Store store = new Store();

        store.setName( dto.getName() );
        store.setLatitude( dto.getLatitude() );
        store.setLongitude( dto.getLongitude() );

        return store;
    }

    @Override
    public List<StoreDto> convertEntityListToDtoList(List<Store> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<StoreDto> list = new ArrayList<StoreDto>( entityList.size() );
        for ( Store store : entityList ) {
            list.add( convertEntityToDto( store ) );
        }

        return list;
    }

    @Override
    public List<Store> convertDtoListToEntityList(List<StoreDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Store> list = new ArrayList<Store>( dtoList.size() );
        for ( StoreDto storeDto : dtoList ) {
            list.add( convertDtoToEntity( storeDto ) );
        }

        return list;
    }
}
