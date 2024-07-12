package com.real_estate.llk_server_spring.Product;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.Product.Entity.ProductRepository;
import com.real_estate.llk_server_spring.Product.Entity.ProductSale;
import com.real_estate.llk_server_spring.Product.Entity.ProductType;
import com.real_estate.llk_server_spring.Product.dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(ProjectDTO projectDTO) {
        Product product = new Product();
        product.setProductName(projectDTO.getName());
        product.setProductDescription(projectDTO.getDescription());
        product.setProductPrice(projectDTO.getPrice());
        product.setProductAdd(projectDTO.getAdd());
        product.setProductType(ProductType.APARTMENT);
        product.setProductArea(projectDTO.getArea());
        product.setProductRoom(projectDTO.getRoom());
        product.setProductBathroom(projectDTO.getBathroom());
        product.setProductYear(projectDTO.getYear());
        product.setProductImg(projectDTO.getImg());
        product.setProductReg(projectDTO.getReg());
        product.setProductCor(projectDTO.getCor());
        product.setProductSale(ProductSale.SALE);
        return productRepository.save(product);
    }
}