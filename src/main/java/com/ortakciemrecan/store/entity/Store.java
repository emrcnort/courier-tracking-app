package com.ortakciemrecan.store.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @JsonProperty("lat")
    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @JsonProperty("lng")
    @Column(name = "longitude", nullable = false)
    private Double longitude;
}
