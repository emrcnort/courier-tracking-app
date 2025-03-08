package com.ortakciemrecan.courier.entity;

import com.ortakciemrecan.store.entity.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class CourierStoreEntryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "courier_id", referencedColumnName = "id", nullable = false)
    private Courier courier;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "store_id", referencedColumnName = "id", nullable = false)
    private Store store;

    @Column(name = "entrance_time", nullable = false)
    private LocalDateTime entranceTime;

    @Column(name = "total_entrance", nullable = false)
    private Double totalEntrance;
}