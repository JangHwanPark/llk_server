package com.real_estate.llk_server_spring.Product;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.Product.dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody ProjectDTO projectDTO) {
        Product createProduct = productService.createProduct(projectDTO);
        return ResponseEntity.ok(createProduct);
    }
}