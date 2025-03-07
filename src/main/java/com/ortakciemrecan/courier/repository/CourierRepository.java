package com.ortakciemrecan.courier.repository;

import com.ortakciemrecan.courier.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, Long> {
    Optional<Courier> findByEmployeeNumber(Long employeeNumber);
}
