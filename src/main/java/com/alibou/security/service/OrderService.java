package com.alibou.security.service;

import com.alibou.security.repository.OrderRepository;
import com.alibou.security.repository.ProductRepository;
import com.alibou.security.user.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public boolean placeOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);

        orderRepository.save(order);
        return true;
    }
}

