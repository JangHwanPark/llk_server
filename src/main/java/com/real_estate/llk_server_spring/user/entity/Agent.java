package com.real_estate.llk_server_spring.user.entity;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "agent", schema = "db2451506_llk")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id", nullable = false)
    private Long id;

    @Column(name = "agent_first_name")
    private String agentFirstName;

    @Column(name = "agent_last_name")
    private String agentLastName;

    @Column(name = "license_number")
    private String licenseNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "agent")
    private List<Product> products;
}