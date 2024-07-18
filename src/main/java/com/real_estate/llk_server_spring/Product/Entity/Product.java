package com.real_estate.llk_server_spring.Product.Entity;

import com.real_estate.llk_server_spring.user.entity.Agent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product", schema = "db2451506_llk")
@NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "product_price", nullable = false, scale = 2)
    private BigDecimal productPrice;

    @NotBlank
    @Column(name = "product_add", nullable = false)
    private String productAdd;

    @Column(name = "product_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "product_area", scale = 1, precision = 10, nullable = false)
    private BigDecimal productArea;

    @Column(name = "product_room", nullable = false)
    private Integer productRoom;

    @Column(name = "product_bathroom", nullable = false)
    private Integer productBathroom;

    @Column(name = "product_year", nullable = false)
    private LocalDate productYear;

    @Column(name = "product_reg", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate productReg;

    @Column(name = "product_cor", nullable = false)
    @UpdateTimestamp
    private LocalDate productCor;

    @Column(name = "product_sale", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductSale productSale;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImgLinkList> productImgLinkList;

    @JoinColumn(name = "agent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;

}