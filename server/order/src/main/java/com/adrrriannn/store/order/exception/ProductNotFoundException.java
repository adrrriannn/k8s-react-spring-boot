package com.adrrriannn.store.order.exception;

public class ProductNotFoundException extends Exception {

    private static final String MESSAGE = "Product not found for id : %s";

    public ProductNotFoundException(String productId) {
        super(String.format(MESSAGE, productId));
    }
}
