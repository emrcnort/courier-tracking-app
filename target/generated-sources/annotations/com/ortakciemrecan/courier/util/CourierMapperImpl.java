package com.ortakciemrecan.courier.util;

import com.ortakciemrecan.courier.dto.CourierDto;
import com.ortakciemrecan.courier.dto.CourierRequest;
import com.ortakciemrecan.courier.entity.Courier;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-09T17:52:31+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class CourierMapperImpl implements CourierMapper {

    @Override
    public CourierDto convertEntityToDto(Courier entity) {
        if ( entity == null ) {
            return null;
        }

        CourierDto courierDto = new CourierDto();

        courierDto.setId( entity.getId() );
        courierDto.setName( entity.getName() );
        courierDto.setEmployeeNumber( entity.getEmployeeNumber() );

        return courierDto;
    }

    @Override
    public Courier convertDtoToEntity(CourierDto dto) {
        if ( dto == null ) {
            return null;
        }

        Courier courier = new Courier();

        courier.setId( dto.getId() );
        courier.setName( dto.getName() );
        courier.setEmployeeNumber( dto.getEmployeeNumber() );

        return courier;
    }

    @Override
    public List<CourierDto> convertEntityListToDtoList(List<Courier> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CourierDto> list = new ArrayList<CourierDto>( entityList.size() );
        for ( Courier courier : entityList ) {
            list.add( convertEntityToDto( courier ) );
        }

        return list;
    }

    @Override
    public List<Courier> convertDtoListToEntityList(List<CourierDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Courier> list = new ArrayList<Courier>( dtoList.size() );
        for ( CourierDto courierDto : dtoList ) {
            list.add( convertDtoToEntity( courierDto ) );
        }

        return list;
    }

    @Override
    public Courier convertRequestToDto(CourierRequest courierRequest) {
        if ( courierRequest == null ) {
            return null;
        }

        Courier courier = new Courier();

        courier.setName( courierRequest.name() );
        courier.setEmployeeNumber( courierRequest.employeeNumber() );

        return courier;
    }
}
