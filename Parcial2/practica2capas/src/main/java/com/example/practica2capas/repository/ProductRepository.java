package com.example.practica2capas.repository;
import com.example.practica2capas.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
   Optional <Product> findByProductName(String name);
}
