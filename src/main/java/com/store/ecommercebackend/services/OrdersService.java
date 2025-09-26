package com.store.ecommercebackend.services;

import com.store.ecommercebackend.dto.response.OrderResponse;
import com.store.ecommercebackend.entities.Order;
import com.store.ecommercebackend.entities.Product;
import com.store.ecommercebackend.entities.User;
import com.store.ecommercebackend.enums.OrderStatus;
import com.store.ecommercebackend.exceptions.ResourceNotFoundException;
import com.store.ecommercebackend.mappers.OrderMapper;
import com.store.ecommercebackend.repositories.OrderRepository;
import com.store.ecommercebackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;
    private final OrderMapper orderMapper;

    // Getting all orders
    public ResponseEntity<?> getAllOrders(Long userId) {
        Optional<User> user=userRepository.findById(userId);

        if(user.isPresent()){
            User newUser=user.get();

            var orders = orderRepository.findByCustomer(newUser);
        List<OrderResponse>listOfOrders=orders.stream().map(orderMapper::toOrderDto).toList();
        return ResponseEntity.ok(listOfOrders);
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.EMPTY_LIST);
        }

    }

    public OrderResponse getSingleOrder(Long orderId,User user) {
        var orders = orderRepository.findByCustomer(user);
        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst().map(orderMapper::toOrderDto)
                .orElseThrow(() -> new ResourceNotFoundException("The above order wasn't found!..."));
    }

    public ResponseEntity<?> saveOrder(Long userId, Long productId) {
        Order order=new Order();
        order.setOrderStatus(OrderStatus.SAVED);
        Optional<User> data=userRepository.findById(userId);
        Optional<Product> productValue=productService.getProductById(productId);
        if(data.isPresent()) {
            order.setCustomer(data.get());
            order.setTotalPrice(productValue.get().getPrice());
            orderRepository.save(order);
            return new ResponseEntity<>(order,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("USER NOT FOUND",HttpStatus.BAD_REQUEST);
        }

    }
}


