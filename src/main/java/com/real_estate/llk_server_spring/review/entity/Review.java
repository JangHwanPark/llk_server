package com.real_estate.llk_server_spring.review.entity;

import com.real_estate.llk_server_spring.user.entity.Agent;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "review")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "review_name", nullable = false)
    private String reviewName;

    @Column(name = "review_description", nullable = false)
    private String reviewDescription;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ReviewType type;

    @Column(name = "review_score", nullable = false, precision =2, scale = 1)
    private BigDecimal reviewScore;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate created;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDate updated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;
}
