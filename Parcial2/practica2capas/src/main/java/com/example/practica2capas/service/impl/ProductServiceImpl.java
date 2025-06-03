package com.example.practica2capas.service.impl;

import com.example.practica2capas.dto.request.product.ProductRequest;
import com.example.practica2capas.dto.request.product.ProductUpdateRequest;
import com.example.practica2capas.dto.response.product.ProductResponse;
import com.example.practica2capas.entities.Product;
import com.example.practica2capas.exception.ProductNotFoundException;
import com.example.practica2capas.repository.ProductRepository;
import com.example.practica2capas.service.ProductService;
import com.example.practica2capas.utils.mappers.ProductMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.productRepository = repository;
    }

    @Override
    public List<ProductResponse> findAll() {
        return ProductMapper.toDTOList(productRepository.findAll());
    }

    @Override
    public ProductResponse findById(Integer id) {
        return ProductMapper.toDTO(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found")));
    }

    @Override
    public ProductResponse findByName(String name) {
        return ProductMapper.toDTO(productRepository.findByProductName(name.toUpperCase())
                .orElseThrow(() -> new ProductNotFoundException("Product not found")));
    }

    @Override
    @Transactional
    public ProductResponse save(ProductRequest product){
        product.setProductName(product.getProductName().toUpperCase());
        return ProductMapper.toDTO(productRepository.save(ProductMapper.toEntityCreate(product)));
    }

    @Override
    @Transactional
    public ProductResponse update(ProductUpdateRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        ProductMapper.toEntityUpdate(request, product);

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toDTO(updatedProduct);

    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }


}
