package com.real_estate.llk_server_spring.entity;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "inquiry", schema = "db2451506_llk")
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "inquiry_date")
    private LocalDate inquiryDate;

    @Size(max = 400)
    @Column(name = "inquiries", length = 400)
    private String inquiries;

    @Size(max = 400)
    @Column(name = "inquiry_answer", length = 400)
    private String inquiryAnswer;

}