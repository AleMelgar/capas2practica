package com.example.practica2capas.dto.request.product;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    @NotNull(message = "Product name cannot be null")
    private String productName;

    @NotNull(message = "Product price cannot be null")
    private Double price;

    @NotNull(message = "Product stock cannot be null")
    private Integer stock;
}
