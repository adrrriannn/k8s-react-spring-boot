package com.adrrriannn.store.order.repository;

import com.adrrriannn.store.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    Cart findByUserId(String userId);
}
