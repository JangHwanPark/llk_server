package com.real_estate.llk_server_spring.user.entity;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.entity.Agentrating;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "agent", schema = "db2451506_llk")
public class Agent {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @Size(max = 30)
    @Column(name = "agent_first_name", length = 30)
    private String agentFirstName;

    @Size(max = 30)
    @Column(name = "agent_last_name", length = 30)
    private String agentLastName;

    @Size(max = 20)
    @Column(name = "license_number", length = 20)
    private String licenseNumber;

    @ColumnDefault("0")
    @Column(name = "average_rating", precision = 3, scale = 2)
    private BigDecimal averageRating;

    @OneToMany(mappedBy = "agent")
    private Set<Agentrating> agentratings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Product> products = new LinkedHashSet<>();

}