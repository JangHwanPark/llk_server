package com.real_estate.llk_server_spring.review.dto;

import com.real_estate.llk_server_spring.review.entity.ReviewType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddReviewDTO {
    private String reviewName;
    private String reviewDescription;
    private String address;
    private String agentLicense;
    private ReviewType reviewType;
    private Integer reviewScore;
}
