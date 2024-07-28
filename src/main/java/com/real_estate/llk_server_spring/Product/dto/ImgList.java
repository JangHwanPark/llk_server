package com.real_estate.llk_server_spring.Product.dto;

import com.real_estate.llk_server_spring.Product.Entity.ProductImgLinkList;

import java.util.List;
import java.util.stream.Collectors;

public record ImgList(
        Long id,
        String imgLink
) {
    public ImgList(ProductImgLinkList list) {
        this(list.getId(), list.getImgLink());
    }

    public static List<ImgList> fromProductImgLinkList(List<ProductImgLinkList> list) {
        return list.stream()
                .map(ImgList::new)
                .collect(Collectors.toList());
    }
}
