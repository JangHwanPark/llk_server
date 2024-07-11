package com.real_estate.llk_server_spring.entity;

import com.real_estate.llk_server_spring.user.entity.Agent;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "agentrating", schema = "db2451506_llk")
public class Agentrating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "rating_value", precision = 3, scale = 1)
    private BigDecimal ratingValue;

    @Column(name = "rating_date")
    private LocalDate ratingDate;

}