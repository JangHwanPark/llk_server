package com.real_estate.llk_server_spring.region.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "US_city")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
}
