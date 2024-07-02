package com.real_estate.llk_server_spring.Product.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Products {
    @Id
    private Long id;
    private String name;
    private String description;
    private String image;
    private int price;
    private int stock;
    private int sold;
    private int rating;
    private int review;
    private int category;
    private int subcategory;
    private int brand;
    private int seller;
    private int created;
    private int updated;

    public Products() {}

    public Products(
            String name,
            String description,
            String image,
            int price,
            int stock,
            int sold,
            int rating,
            int review,
            int category,
            int subcategory,
            int brand,
            int seller
    ) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.sold = sold;
        this.rating = rating;
        this.review = review;
        this.category = category;
        this.subcategory = subcategory;
        this.brand = brand;
        this.seller = seller;
    }
}