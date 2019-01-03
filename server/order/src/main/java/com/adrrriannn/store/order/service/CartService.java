package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.CartDto;

public interface CartService {

    CartDto updateCart(CartDto cartDto);
    CartDto getCartByUser(String userId);
}
