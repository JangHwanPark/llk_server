package com.real_estate.llk_server_spring.Product;

import com.real_estate.llk_server_spring.Product.Entity.Product;
import com.real_estate.llk_server_spring.Product.Entity.ProductRepository;
import com.real_estate.llk_server_spring.Product.dto.ProductListDTO;
import com.real_estate.llk_server_spring.Product.dto.ProjectDTO;
import com.real_estate.llk_server_spring.exception.LlkServerException;
import com.real_estate.llk_server_spring.util.LlkUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final LlkUtil llkUtil;

    public ResponseEntity<?> createProductProc(ProjectDTO projectDTO, List<MultipartFile> files) {
        try {
            llkUtil.usingStringDataValidationCheck(projectDTO.getName());
            llkUtil.usingStringDataValidationCheck(projectDTO.getDescription());
            llkUtil.usingStringDataValidationCheck(String.valueOf(projectDTO.getPrice()));
            llkUtil.usingStringDataValidationCheck(projectDTO.getAdd());
            llkUtil.usingStringDataValidationCheck(String.valueOf(projectDTO.getType()));
            llkUtil.usingStringDataValidationCheck(String.valueOf(projectDTO.getArea()));
            llkUtil.usingStringDataValidationCheck(String.valueOf(projectDTO.getRoom()));
            llkUtil.usingStringDataValidationCheck(String.valueOf(projectDTO.getBathroom()));
            llkUtil.usingStringDataValidationCheck(String.valueOf(projectDTO.getYear()));
            llkUtil.usingStringDataValidationCheck(String.valueOf(projectDTO.getSale()));
            llkUtil.usingStringDataValidationCheck(files.toString());
        } catch (LlkServerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // img file upload logic
        Product product = new Product().builder()
                .productName(projectDTO.getName())
                .productDescription(projectDTO.getDescription())
                .productPrice(BigDecimal.valueOf(projectDTO.getPrice()))
                .productAdd(projectDTO.getAdd())
                .productType(projectDTO.getType())
                .productArea(BigDecimal.valueOf(projectDTO.getArea()))
                .productRoom(projectDTO.getRoom())
                .productBathroom(projectDTO.getBathroom())
                .productYear(projectDTO.getYear())
                .productSale(projectDTO.getSale())
                .build();
        productRepository.save(product);
        llkUtil.imgFileUploadProc(files,product,"hojung");

        return ResponseEntity.status(HttpStatus.CREATED).body("product created successfully");
    }

    public ResponseEntity<?> deleteProductProc(String id) {
        return null;
    }

    public List<ProductListDTO> getProductListProc() {
        return productRepository.findAll().stream()
                .map(ProductListDTO::new)
                .toList();
    }
}