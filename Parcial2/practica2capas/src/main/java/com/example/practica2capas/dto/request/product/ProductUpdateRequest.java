package com.example.practica2capas.dto.request.product;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUpdateRequest {
    private Integer productId;
    private String productName;
    private Double price;
    private Integer stock;

}
