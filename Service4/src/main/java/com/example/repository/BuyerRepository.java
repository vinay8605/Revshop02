package com.example.repository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.example.entity.Buyer;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    List<Buyer> findAllByOrderByIdAsc();
}
