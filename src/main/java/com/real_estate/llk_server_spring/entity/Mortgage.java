package com.real_estate.llk_server_spring.entity;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "mortgage", schema = "db2451506_llk")
public class Mortgage {
    @Id
    @ColumnDefault("nextval('db2451506_llk.mortgage_mortgage_id_seq'::regclass)")
    @Column(name = "mortgage_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "mortgage_money", precision = 15, scale = 2)
    private BigDecimal mortgageMoney;

    @Column(name = "mortgage_interest", precision = 5, scale = 4)
    private BigDecimal mortgageInterest;

    @Column(name = "mortgage_start_date")
    private LocalDate mortgageStartDate;

    @Column(name = "mortgage_end_date")
    private LocalDate mortgageEndDate;

    @Column(name = "mortgage_repayment", precision = 15, scale = 2)
    private BigDecimal mortgageRepayment;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Size(max = 20)
    @Column(name = "approval_status", length = 20)
    private String approvalStatus;

}