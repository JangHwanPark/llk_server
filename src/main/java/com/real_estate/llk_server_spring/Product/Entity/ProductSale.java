package com.real_estate.llk_server_spring.Product.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductSale {
    SALE("SALE", "판매중"), SOLD_OUT("SOLD_OUT", "판매 완료");

    private final String eng;
    private final String kor;
}
