package com.example.practica2capas.utils.mappers;

import com.example.practica2capas.dto.request.product.ProductRequest;
import com.example.practica2capas.dto.request.product.ProductUpdateRequest;
import com.example.practica2capas.dto.response.product.ProductResponse;
import com.example.practica2capas.entities.Product;

import java.util.List;

public class ProductMapper {

    public static Product toEntity(ProductResponse productDTO) {
        return Product.builder()
                .id(productDTO.getProductId())
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();
    }

    public static Product toEntityCreate(ProductRequest productDTO){
        return Product.builder()
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .build();
    }

    public static void toEntityUpdate(ProductUpdateRequest productDTO, Product product) {
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

    }

    public static ProductResponse toDTO(Product product) {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    public static List<ProductResponse> toDTOList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

}
