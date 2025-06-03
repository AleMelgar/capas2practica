package com.example.practica2capas.controller;

import com.example.practica2capas.dto.request.order.OrderRequest;
import com.example.practica2capas.dto.request.order.OrderUpdateRequest;
import com.example.practica2capas.dto.response.GeneralResponse;
import com.example.practica2capas.dto.response.client.ClientResponse;
import com.example.practica2capas.dto.response.order.OrderResponse;
import com.example.practica2capas.exception.OrderNotFoundException;
import com.example.practica2capas.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> getAllOrders() {
        List<OrderResponse> orders = orderService.findAll();

        if(orders.isEmpty()) {
            throw new OrderNotFoundException("No orders found");
        }

        return buildResponse("Orders found", HttpStatus.OK, orders);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getOrderById(@PathVariable int id) {
        OrderResponse order = orderService.findById(id);
        return buildResponse("Order found", HttpStatus.OK, order);
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        return buildResponse("Order created", HttpStatus.CREATED, orderService.save(orderRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateOrder(@PathVariable int id, @RequestBody @Valid OrderUpdateRequest order) {
        order.setOrderId(id);
        OrderResponse updatedOrder = orderService.update(order);
        return buildResponse("Order updated", HttpStatus.OK, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteOrder(@PathVariable int id) {
        OrderResponse order = orderService.findById(id);
        orderService.delete(id);
        return buildResponse("Order deleted", HttpStatus.OK, order);
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
