package com.ortakciemrecan.courier.utils;

import com.ortakciemrecan.common.utils.BaseMapper;
import com.ortakciemrecan.courier.dto.CourierDto;
import com.ortakciemrecan.courier.dto.CourierRequest;
import com.ortakciemrecan.courier.entity.Courier;
import org.mapstruct.Mapper;

@Mapper
public interface CourierMapper extends BaseMapper<Courier, CourierDto> {
    Courier convertRequestToDto(CourierRequest courierRequest);
}
