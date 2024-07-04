package com.real_estate.llk_server_spring.Product.Entity;

import com.real_estate.llk_server_spring.Product.dto.ProjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}