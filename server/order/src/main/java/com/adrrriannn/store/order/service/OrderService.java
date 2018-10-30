package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.OrderDto;

public interface OrderService {

    OrderDto processOrder(OrderDto orderDto);
    OrderDto getOrderById(String id);
}
