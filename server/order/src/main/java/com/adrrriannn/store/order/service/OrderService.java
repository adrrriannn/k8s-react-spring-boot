package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.order.exception.ProductNotFoundException;

public interface OrderService {

    OrderDto processOrder(OrderDto orderDto) throws ProductNotFoundException;
    OrderDto getOrderById(String id);
}
