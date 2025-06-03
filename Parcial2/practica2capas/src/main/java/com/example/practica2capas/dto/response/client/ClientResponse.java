package com.example.practica2capas.dto.response.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponse {
    private Integer clientId;
    private String name;
    private String email;
    private String phone;
}
