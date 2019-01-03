package com.adrrriannn.store.order.controller;

import com.adrrriannn.store.dto.CartDto;
import com.adrrriannn.store.order.exception.ProductNotFoundException;
import com.adrrriannn.store.order.service.CartService;
import com.adrrriannn.store.order.validator.CartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public class CartController {
    private CartService cartService;
    private CartValidator cartValidator;

    @Autowired
    public CartController(CartService cartService,
                           CartValidator cartValidator) {
        this.cartService = cartService;
        this.cartValidator = cartValidator;
    }

    @InitBinder("cart")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(cartValidator);
    }

    @PutMapping
    public CartDto updateCart(@RequestBody @Valid CartDto cart) throws ProductNotFoundException {
        return cartService.updateCart(cart);
    }

    @GetMapping
    public CartDto getCart() {
        String userId = "userId";
        return cartService.getCartByUser(userId);
    }
}
