package com.ortakciemrecan.courier.util;

import com.ortakciemrecan.common.utils.BaseMapper;
import com.ortakciemrecan.courier.dto.CourierDto;
import com.ortakciemrecan.courier.dto.CourierRequest;
import com.ortakciemrecan.courier.entity.Courier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourierMapper extends BaseMapper<Courier, CourierDto> {
    Courier convertRequestToDto(CourierRequest courierRequest);
}
