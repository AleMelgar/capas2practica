package com.example.practica2capas.service.impl;

import com.example.practica2capas.dto.request.client.ClientRequest;
import com.example.practica2capas.dto.request.client.ClientUpdateRequest;
import com.example.practica2capas.dto.response.client.ClientResponse;
import com.example.practica2capas.entities.Client;
import com.example.practica2capas.exception.ClientNotFoundException;
import com.example.practica2capas.repository.ClientRepository;
import com.example.practica2capas.service.ClientService;
import com.example.practica2capas.utils.mappers.ClientMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.clientRepository = repository;
    }

    @Override
    public List<ClientResponse> findAll() {
        return ClientMapper.toDTOList(clientRepository.findAll());
    }

    @Override
    public ClientResponse findById(Integer id) {
        return ClientMapper.toDTO(clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found")));
    }


    @Override
    @Transactional
    public ClientResponse save(ClientRequest client) {
        client.setName(client.getName().toUpperCase());
        return ClientMapper.toDTO(clientRepository.save(ClientMapper.toEntityCreate(client)));
    }

    @Override
    @Transactional
    public ClientResponse update(ClientUpdateRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        ClientMapper.toEntityUpdate(request, client);

        Client updatedClient = clientRepository.save(client);

        return ClientMapper.toDTO(updatedClient);
    }

    @Override
    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }


}
