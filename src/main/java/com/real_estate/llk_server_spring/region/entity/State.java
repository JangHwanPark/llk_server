package com.real_estate.llk_server_spring.region.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "US_state")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state_name", nullable = false, unique = true)
    private String stateName;

    @Column(name= "state_abbreviation", nullable = false, unique = true)
    private String stateAbbreviation;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<City> cities;
}
