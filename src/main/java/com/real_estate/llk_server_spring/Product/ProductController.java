package com.real_estate.llk_server_spring.Product;

import com.real_estate.llk_server_spring.Product.dto.ProjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> createProduct(@RequestPart(value = "data") ProjectDTO projectDTO,
                                                 @RequestPart(name = "file") List<MultipartFile> files) {
        return productService.createProductProc(projectDTO, files);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody String id) {
        return productService.deleteProductProc(id);
    }
}