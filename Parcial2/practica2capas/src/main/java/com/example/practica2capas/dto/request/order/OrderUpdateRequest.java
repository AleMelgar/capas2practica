package com.example.practica2capas.dto.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderUpdateRequest {
    private Integer orderId;
    private Double amount;
    private LocalDate date;
    private Integer clientId;
    private Integer productId;


}
