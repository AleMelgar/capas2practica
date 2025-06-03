package com.example.practica2capas.service.impl;

import com.example.practica2capas.dto.request.order.OrderRequest;
import com.example.practica2capas.dto.request.order.OrderUpdateRequest;
import com.example.practica2capas.dto.response.order.OrderResponse;
import com.example.practica2capas.entities.Client;
import com.example.practica2capas.entities.Order;
import com.example.practica2capas.entities.Product;
import com.example.practica2capas.exception.ClientNotFoundException;
import com.example.practica2capas.exception.OrderNotFoundException;
import com.example.practica2capas.exception.ProductNotFoundException;
import com.example.practica2capas.repository.ClientRepository;
import com.example.practica2capas.repository.OrderRepository;
import com.example.practica2capas.repository.ProductRepository;
import com.example.practica2capas.service.ClientService;
import com.example.practica2capas.service.OrderService;
import com.example.practica2capas.service.ProductService;
import com.example.practica2capas.utils.mappers.OrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<OrderResponse> findAll(){
        return OrderMapper.toDTOList(orderRepository.findAll());
    }

    @Override
    public OrderResponse findById(Integer id) {
        return OrderMapper.toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found")));
    }

    @Override
    @Transactional
    public OrderResponse save(OrderRequest orderRequest) {

        Client client = clientRepository.findById(orderRequest.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Product product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Order order = OrderMapper.toEntityCreate(orderRequest, client, product);

        Order savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponse update(OrderUpdateRequest request){
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        OrderMapper.toEntityUpdate(request, order, client, product);
        Order updatedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(updatedOrder);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }

}
