package com.example.practica2capas.controller;

import com.example.practica2capas.dto.request.client.ClientRequest;
import com.example.practica2capas.dto.request.client.ClientUpdateRequest;
import com.example.practica2capas.dto.response.GeneralResponse;
import com.example.practica2capas.dto.response.client.ClientResponse;
import com.example.practica2capas.exception.ClientNotFoundException;
import com.example.practica2capas.service.ClientService;
import com.example.practica2capas.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<GeneralResponse> getAllClients() {
        List<ClientResponse> clients = clientService.findAll();

        if (clients.isEmpty()) {
           throw new ClientNotFoundException("No clients found");
        }

        return buildResponse("Clients found", HttpStatus.OK, clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getClientById(@PathVariable int id) {
        ClientResponse client = clientService.findById(id);
        return buildResponse("Client found", HttpStatus.OK, client);
    }

    @PostMapping()
    public ResponseEntity<GeneralResponse> createClient(@RequestBody @Valid ClientRequest clientRequest) {
        return buildResponse("Client created", HttpStatus.CREATED, clientService.save(clientRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateClient(@PathVariable int id, @RequestBody @Valid ClientUpdateRequest client) {
        client.setClientId(id);
        ClientResponse updatedClient = clientService.update(client);
        return buildResponse("Client updated", HttpStatus.OK, updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteClient(@PathVariable int id) {
        ClientResponse client = clientService.findById(id);
        clientService.delete(id);
        return buildResponse("Client deleted", HttpStatus.OK, client);
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status).body(GeneralResponse.builder()
                .message(message)
                .status(status.value())
                .data(data)
                .uri(uri)
                .time(LocalDate.now())
                .build());
    }

}
