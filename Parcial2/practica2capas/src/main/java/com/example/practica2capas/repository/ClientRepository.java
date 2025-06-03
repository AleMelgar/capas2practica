package com.example.practica2capas.repository;

import com.example.practica2capas.entities.Client;
import com.example.practica2capas.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
