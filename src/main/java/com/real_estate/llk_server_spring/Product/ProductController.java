package com.real_estate.llk_server_spring.Product;

import com.real_estate.llk_server_spring.Product.dto.ProjectDTO;
import com.real_estate.llk_server_spring.common.CommonDTO;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProductProc(id);
        if (isDeleted) {
            return ResponseEntity.ok(CommonDTO.success("Product deleted successfully."));
        } else {
            return ResponseEntity.ok(CommonDTO.fail("Product not found."));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getProductList() {
        return ResponseEntity.ok(CommonDTO.success(productService.getProductListProc()));
    }
}