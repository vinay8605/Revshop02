package com.example.add.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Orders;
import com.example.repository.OrderRepository;

import jakarta.persistence.criteria.Order;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Get all orders ordered by ID in ascending order
    public List<Orders> getAllOrders() {
        return orderRepository.findAllByOrderByIdAsc();
    }

    // Get orders filtered by date, product name, or buyer name
    public List<Orders> getFilteredOrders(Date date, String productName, String buyerName) {
        return orderRepository.findByCreatedAtAndProductNameContainingAndBuyerNameContaining(date, productName, buyerName);
    }

    // Get a single order by ID
    public Optional<Orders> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);  // Returns Optional<Order>
    }

    // Update order status
    @Transactional
    public void updateOrderStatus(Long orderId, String status) {
        Optional<Orders> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            ((Orders) order).setStatus(status);
            orderRepository.save(order);  // Save updated order
        } else {
            // Handle case where order is not found (optional)
            throw new RuntimeException("Order not found for ID: " + orderId);
        }
    }
}
