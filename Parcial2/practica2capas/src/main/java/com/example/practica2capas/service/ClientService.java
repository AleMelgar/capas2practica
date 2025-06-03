package com.example.practica2capas.service;

import com.example.practica2capas.dto.request.client.ClientRequest;
import com.example.practica2capas.dto.request.client.ClientUpdateRequest;
import com.example.practica2capas.dto.response.client.ClientResponse;

import java.util.List;

public interface ClientService {
    List<ClientResponse> findAll();
    ClientResponse findById(Integer id);
    ClientResponse save(ClientRequest client);
    ClientResponse update(ClientUpdateRequest client);
    void delete(Integer id);
}
