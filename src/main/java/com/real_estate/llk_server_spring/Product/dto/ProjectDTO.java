package com.real_estate.llk_server_spring.Product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
public class ProjectDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private String add;
    private String type;
    private BigDecimal area;
    private Integer room;
    private Integer bathroom;
    private LocalDate year;
    private String img;
    private LocalDate reg;
    private LocalDate cor;
    private String sale;
    private Integer count;
}