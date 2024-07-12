package com.real_estate.llk_server_spring.Product.Entity;

public enum ProductSale {
    SALE("SALE", "판매중"), SOLD_OUT("SOLD_OUT", "판매 완료");

    private final String eng;
    private final String kor;
    private ProductSale(String eng, String kor) {
        this.eng = eng;
        this.kor = kor;
    }
    public String getEng() {
        return eng;
    }
    public String getKor() {
        return kor;
    }
}
