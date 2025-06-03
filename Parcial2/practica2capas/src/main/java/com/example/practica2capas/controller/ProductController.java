package com.example.practica2capas.controller;

import com.example.practica2capas.dto.request.product.ProductRequest;
import com.example.practica2capas.dto.request.product.ProductUpdateRequest;
import com.example.practica2capas.dto.response.GeneralResponse;
import com.example.practica2capas.dto.response.product.ProductResponse;
import com.example.practica2capas.exception.ClientNotFoundException;
import com.example.practica2capas.exception.ProductNotFoundException;
import com.example.practica2capas.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<GeneralResponse> getAllProducts() {
        List<ProductResponse> products = productService.findAll();

        if(products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return buildResponse("Products found", HttpStatus.OK, products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getProductById(@PathVariable int id) {
        ProductResponse product = productService.findById(id);
        return buildResponse("Product found", HttpStatus.OK, product);
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return buildResponse("Product created", HttpStatus.CREATED, productService.save(productRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateProduct(@PathVariable int id, @RequestBody @Valid ProductUpdateRequest product){
        product.setProductId(id);
        ProductResponse updatedProduct = productService.update(product);
        return buildResponse("Product updated", HttpStatus.OK, updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteProduct(@PathVariable int id) {
        ProductResponse product = productService.findById(id);
        productService.delete(id);
        return buildResponse("Product deleted", HttpStatus.OK, product);
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
