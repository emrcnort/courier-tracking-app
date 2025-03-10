package com.ortakciemrecan.courier.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierDto {
    private Long id;
    private String name;
    private Long employeeNumber;
    private boolean isActive;
}
