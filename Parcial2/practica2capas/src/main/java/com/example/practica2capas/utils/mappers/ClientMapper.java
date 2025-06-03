package com.example.practica2capas.utils.mappers;

import com.example.practica2capas.dto.request.client.ClientRequest;
import com.example.practica2capas.dto.request.client.ClientUpdateRequest;
import com.example.practica2capas.dto.response.client.ClientResponse;
import com.example.practica2capas.entities.Client;

import java.util.List;

public class ClientMapper {


    public static Client toEntity(ClientResponse clientDTO) {
        return Client.builder()
                .id(clientDTO.getClientId())
                .name(clientDTO.getName())
                .email(clientDTO.getEmail())
                .phone(clientDTO.getPhone())
                .build();
    }

    public static Client toEntityCreate(ClientRequest clientDTO) {
        return Client.builder()
                .name(clientDTO.getName())
                .email(clientDTO.getEmail())
                .phone(clientDTO.getPhone())
                .build();
    }

    public static void toEntityUpdate(ClientUpdateRequest clientDTO, Client client) {
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());

    }

    public static ClientResponse toDTO(Client client) {
        return ClientResponse.builder()
                .clientId(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .build();
    }

    public static List<ClientResponse> toDTOList(List<Client> clients) {
        return clients.stream()
                .map(ClientMapper::toDTO)
                .toList();
    }
}
