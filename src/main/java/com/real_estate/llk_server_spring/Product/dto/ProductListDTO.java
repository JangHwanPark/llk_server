package com.real_estate.llk_server_spring.Product.dto;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.Product.Entity.ProductImgLinkList;
import com.real_estate.llk_server_spring.Product.Entity.ProductSale;
import com.real_estate.llk_server_spring.Product.Entity.ProductType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProductListDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String add,
        String type,
        BigDecimal area,
        Integer room,
        Integer bathroom,
        LocalDate year,
        LocalDate reg,
        String sale,
        List<ImgList> imgLink
) {
    public ProductListDTO(Product product) {
        this(product.getId(), product.getProductName(), product.getProductDescription(),
                product.getProductPrice(), product.getProductAdd(), String.valueOf(product.getProductType()),
                product.getProductArea(), product.getProductRoom(), product.getProductBathroom(),
                product.getProductYear(), product.getProductReg(), String.valueOf(product.getProductSale()),
                ImgList.fromProductImgLinkList(product.getProductImgLinkList())
        );
    }

}
