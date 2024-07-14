package com.real_estate.llk_server_spring.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddReviewDTO {
    private String reviewName;
    private String reviewDescription;
    private String address;
}
