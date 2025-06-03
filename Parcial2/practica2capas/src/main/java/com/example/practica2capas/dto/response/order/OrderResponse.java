package com.example.practica2capas.dto.response.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderResponse {
    private Integer orderId;
    private Double amount;
    private LocalDate date;
    private String clientName;
    private String productName;

}
