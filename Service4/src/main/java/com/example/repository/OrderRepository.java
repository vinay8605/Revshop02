package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Orders;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    
    // Fetch all orders ordered by ID in ascending order
    List<Orders> findAllByOrderByIdAsc();

    // Fetch orders by buyer name (assuming Buyer has a 'name' field)
    List<Orders> findByBuyerName(String buyerName);

    // Fetch orders by product name (assuming Product has a 'name' field)
    List<Orders> findByProductName(String productName);

    // Fetch orders by created date
    List<Orders> findByCreatedAt(Date createdAt);

    // Custom query to filter orders by date, product name, or buyer name
    List<Orders> findByCreatedAtAndProductNameContainingAndBuyerNameContaining(
            Date createdAt, String productName, String buyerName);
    
    // Fetch a single order by ID
    Optional<Orders> findById(Long orderId);
    
    // You can also implement custom queries using @Query annotations if needed
}
