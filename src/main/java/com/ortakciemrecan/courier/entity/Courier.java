package com.ortakciemrecan.courier.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "employee_number", updatable = false, nullable = false)
    @NotNull
    private Long employeeNumber;

    @Column(name = "is_active")
    private Boolean isActive = Boolean.TRUE;

}
