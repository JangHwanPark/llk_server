package com.real_estate.llk_server_spring.Product.dto;

import com.real_estate.llk_server_spring.Product.Entity.ProductSale;
import com.real_estate.llk_server_spring.Product.Entity.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ProjectDTO {
    private String name;
    private String description;
    private Float price;
    private String add;
    private ProductType type;
    private Float area;
    private Integer room;
    private Integer bathroom;
    private LocalDate year;
    private ProductSale sale;
}