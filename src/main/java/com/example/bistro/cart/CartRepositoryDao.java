package com.example.bistro.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepositoryDao extends JpaRepository<Cart, Integer> {
    List<Cart> findByMemberId(Integer memberId);

    void deleteByMemberId(Integer memberId);
}
