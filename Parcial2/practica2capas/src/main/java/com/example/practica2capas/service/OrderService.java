package com.example.practica2capas.service;

import com.example.practica2capas.dto.request.order.OrderRequest;
import com.example.practica2capas.dto.request.order.OrderUpdateRequest;
import com.example.practica2capas.dto.response.order.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> findAll();
    OrderResponse findById(Integer id);
    OrderResponse save(OrderRequest order);
    OrderResponse update(OrderUpdateRequest order);
    void delete(Integer id);
}
