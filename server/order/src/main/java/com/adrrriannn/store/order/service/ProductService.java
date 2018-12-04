package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.order.exception.ProductNotFoundException;

public interface ProductService {

    ProductDto getProductById(String id) throws ProductNotFoundException;
}
