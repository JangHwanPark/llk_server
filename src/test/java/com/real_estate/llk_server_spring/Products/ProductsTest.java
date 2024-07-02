package com.real_estate.llk_server_spring.Products;

import com.real_estate.llk_server_spring.Product.Entity.Products;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductsTest {
    @Test
    public void testProductsConstructorAndGetters() {
        Products product = new Products(
                "Test Product",
                "This is a test product",
                "image.png",
                100,
                10,
                5,
                4,
                2,
                1,
                2,
                3,
                4
        );

        assertEquals("Test Product", product.getName(), "제품 이름이 맞는지 확인");
        assertEquals("This is a test product", product.getDescription(), "제품 설명이 맞는지 확인");
        assertEquals("image.png", product.getImage(), "이미지 경로가 맞는지 확인");
        assertEquals(100, product.getPrice(), "가격이 맞는지 확인");
        assertEquals(10, product.getStock(), "재고가 맞는지 확인");
        assertEquals(5, product.getSold(), "판매된 수량이 맞는지 확인");
        assertEquals(4, product.getRating(), "평점이 맞는지 확인");
        assertEquals(2, product.getReview(), "리뷰 수가 맞는지 확인");
        assertEquals(1, product.getCategory(), "카테고리가 맞는지 확인");
        assertEquals(2, product.getSubcategory(), "서브카테고리가 맞는지 확인");
        assertEquals(3, product.getBrand(), "브랜드가 맞는지 확인");
        assertEquals(4, product.getSeller(), "판매자가 맞는지 확인");
    }

    @Test
    public void testProductsSetters() {
        Products product = new Products();
        product.setName("Updated Product");
        product.setDescription("This is an updated test product");
        product.setImage("updated_image.png");
        product.setPrice(200);
        product.setStock(20);
        product.setSold(10);
        product.setRating(5);
        product.setReview(3);
        product.setCategory(2);
        product.setSubcategory(3);
        product.setBrand(4);
        product.setSeller(5);

        assertEquals("Updated Product", product.getName(), "제품 이름이 업데이트되었는지 확인");
        assertEquals("This is an updated test product", product.getDescription(), "제품 설명이 업데이트되었는지 확인");
        assertEquals("updated_image.png", product.getImage(), "이미지 경로가 업데이트되었는지 확인");
        assertEquals(200, product.getPrice(), "가격이 업데이트되었는지 확인");
        assertEquals(20, product.getStock(), "재고가 업데이트되었는지 확인");
        assertEquals(10, product.getSold(), "판매된 수량이 업데이트되었는지 확인");
        assertEquals(5, product.getRating(), "평점이 업데이트되었는지 확인");
        assertEquals(3, product.getReview(), "리뷰 수가 업데이트되었는지 확인");
        assertEquals(2, product.getCategory(), "카테고리가 업데이트되었는지 확인");
        assertEquals(3, product.getSubcategory(), "서브카테고리가 업데이트되었는지 확인");
        assertEquals(4, product.getBrand(), "브랜드가 업데이트되었는지 확인");
        assertEquals(5, product.getSeller(), "판매자가 업데이트되었는지 확인");
    }
}