package com.example.practica2capas.service;

import com.example.practica2capas.dto.request.product.ProductRequest;
import com.example.practica2capas.dto.request.product.ProductUpdateRequest;
import com.example.practica2capas.dto.response.product.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Integer id);
    ProductResponse findByName(String name);
    ProductResponse save(ProductRequest product);
    ProductResponse update(ProductUpdateRequest product);
    void delete(Integer id);
}
