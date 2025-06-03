package com.example.practica2capas.dto.request.client;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientUpdateRequest {
    private Integer clientId;
    private String name;
    private String email;
    private String phone;
}
