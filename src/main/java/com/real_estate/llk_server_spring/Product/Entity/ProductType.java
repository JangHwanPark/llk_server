package com.real_estate.llk_server_spring.Product.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductType {
    APARTMENT("APARTMENT", "아파트"), HOUSE("HOUSE","주택"),
    COMMERCIAL_LAND("COMMERCIAL LAND", "상업용 토지"), ETC("ETC", "기타");

    private final String eng;
    private final String kor;
}
