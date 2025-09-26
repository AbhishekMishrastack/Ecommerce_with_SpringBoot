package com.store.ecommercebackend.controllers;

import com.store.ecommercebackend.dto.response.OrderResponse;
import com.store.ecommercebackend.entities.User;
import com.store.ecommercebackend.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    // Getting all orders for a user
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(@RequestParam Long userId) {
        return ordersService.getAllOrders(userId);

    }

    // Getting a single order
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getSingleOrder(
            @PathVariable Long orderId,@RequestBody User user
    ) {
        var orderResponse = ordersService.getSingleOrder(orderId,user);
        return ResponseEntity.ok(orderResponse);
    }
    @PostMapping("/addorder")
    public ResponseEntity<?> saveOrder(@RequestParam Long userId, @RequestParam Long productId){
        return ordersService.saveOrder(userId,productId);
    }
}
