package com.example.practica2capas.dto.response.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {
    private Integer productId;
    private String productName;
    private Double price;
    private Integer stock;
}
