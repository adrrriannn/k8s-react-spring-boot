package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.CartDto;
import com.adrrriannn.store.order.entity.Cart;
import com.adrrriannn.store.order.mapper.CartMapper;
import com.adrrriannn.store.order.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartMapper cartMapper;
    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartMapper cartMapper,
                           CartRepository cartRepository) {
        this.cartMapper = cartMapper;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto updateCart(CartDto cartDto) {
        Cart cart = cartMapper.map(cartDto);
        cart = cartRepository.save(cart);
        return cartMapper.map(cart);
    }

    @Override
    public CartDto getCartByUser(String userId) {
        Cart cart = cartRepository.findByUserId(userId);
        return cartMapper.map(cart);
    }
}
