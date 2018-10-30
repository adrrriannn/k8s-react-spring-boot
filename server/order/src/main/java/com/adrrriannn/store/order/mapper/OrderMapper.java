package com.adrrriannn.store.order.mapper;

import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto map(Order order) {
        if(order == null) {
            return OrderDto.builder().build();
        }

        return OrderDto.builder()
                .id(order.getId())
                .email(order.getEmail())
                .productId(order.getProductId())
                .build();
    }

    public Order map(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .email(orderDto.getEmail())
                .productId(orderDto.getProductId())
                .build();
    }
}
