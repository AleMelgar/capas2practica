package com.example.practica2capas.dto.request.client;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRequest {

    @NotNull(message = "Client name cannot be null")
    private String name;

    @NotNull(message = "Client email cannot be null")
    private String email;

    @NotNull(message = "Client phone cannot be null")
    private String phone;

}
