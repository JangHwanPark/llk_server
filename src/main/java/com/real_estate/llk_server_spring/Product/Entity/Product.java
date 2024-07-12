package com.real_estate.llk_server_spring.Product.Entity;

import com.real_estate.llk_server_spring.user.entity.Agent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "product", schema = "db2451506_llk")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @NotBlank
    @Column(name = "product_add")
    private String productAdd;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(name = "product_area")
    private BigDecimal productArea;

    @Column(name = "product_room")
    private Integer productRoom;

    @Column(name = "product_bathroom")
    private Integer productBathroom;

    @Column(name = "product_year")
    private LocalDate productYear;

    @Column(name = "product_img")
    private String productImg;

    @Column(name = "product_reg")
    private LocalDate productReg;

    @Column(name = "product_cor")
    private LocalDate productCor;

    @Column(name = "product_sale")
    @Enumerated(EnumType.STRING)
    private ProductSale productSale;

    @JoinColumn(name = "agent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;

}