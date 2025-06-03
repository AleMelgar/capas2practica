package com.example.practica2capas.utils.mappers;

import com.example.practica2capas.dto.request.order.OrderRequest;
import com.example.practica2capas.dto.request.order.OrderUpdateRequest;
import com.example.practica2capas.dto.response.order.OrderResponse;
import com.example.practica2capas.entities.Client;
import com.example.practica2capas.entities.Order;
import com.example.practica2capas.entities.Product;

import java.util.List;

public class OrderMapper {

    public static Order toEntityCreate(OrderRequest orderDTO, Client client, Product product){
        return Order.builder()
                .amount(orderDTO.getAmount())
                .date(orderDTO.getDate())
                .client(client)
                .product(product)
                .build();
    }

    public static void toEntityUpdate(OrderUpdateRequest orderDTO, Order order, Client client, Product product){
        order.setAmount(orderDTO.getAmount());
        order.setDate(orderDTO.getDate());
        order.setClient(client);
        order.setProduct(product);
    }

    public static OrderResponse toDTO(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .amount(order.getAmount())
                .date(order.getDate())
                .productName(order.getProduct().getProductName())
                .clientName(order.getClient().getName())
                .build();
    }

    public static List<OrderResponse> toDTOList(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::toDTO)
                .toList();
    }
}
