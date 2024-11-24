package com.example.bistro.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositoryDao extends JpaRepository<Payment, Integer> {
}
