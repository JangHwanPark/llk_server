package com.real_estate.llk_server_spring.Product.Entity;

import com.real_estate.llk_server_spring.entity.Agent;
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
@Table(name = "product", schema = "db2451506_llk")
public class Product {
    @Id
    @ColumnDefault("nextval('db2451506_llk.product_product_id_seq'::regclass)")
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "product_name", length = 50)
    private String productName;

    @Size(max = 300)
    @Column(name = "product_description", length = 300)
    private String productDescription;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Size(max = 200)
    @Column(name = "product_add", length = 200)
    private String productAdd;

    @Size(max = 20)
    @Column(name = "product_type", length = 20)
    private String productType;

    @Column(name = "product_area")
    private BigDecimal productArea;

    @Column(name = "product_room")
    private Integer productRoom;

    @Column(name = "product_bathroom")
    private Integer productBathroom;

    @Column(name = "product_year")
    private LocalDate productYear;

    @Size(max = 200)
    @Column(name = "product_img", length = 200)
    private String productImg;

    @Column(name = "product_reg")
    private LocalDate productReg;

    @Column(name = "product_cor")
    private LocalDate productCor;

    @Size(max = 20)
    @Column(name = "product_sale", length = 20)
    private String productSale;

    @ColumnDefault("0")
    @Column(name = "favorite_count")
    private Integer favoriteCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Agent user;

}