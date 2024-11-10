package com.example.add.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Buyer;
import com.example.repository.BuyerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    // Get all buyers
    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAllByOrderByIdAsc();
    }

    // Update account status (activate or deactivate)
    public void updateBuyerStatus(int id, boolean accountStatus) {
        Optional<Buyer> buyer = buyerRepository.findById(id);
        if (buyer.isPresent()) {
            buyer.get().setAccountStatus(accountStatus);
            buyerRepository.save(buyer.get());
        }
    }
}
