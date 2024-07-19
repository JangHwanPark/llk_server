package com.real_estate.llk_server_spring.Product.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_img_link_list")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductImgLinkList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_img_link_list_id")
    private Long id;

    @Column(name = "img_link")
    private String imgLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
