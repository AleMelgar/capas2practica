package com.example.practica2capas.dto.request.order;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderRequest {

    @NotNull(message = "You most provide an order amount")
    private Double amount;

    @NotNull(message = "You must provide a date")
    private LocalDate date;

    @NotNull(message = "You must provide a client ID")
    private Integer clientId;

    @NotNull(message = "You must provide a product ID")
    private Integer productId;

}
