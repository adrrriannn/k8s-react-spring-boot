package com.adrrriannn.store.order.mapper;

import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.dto.OrderItemDto;
import com.adrrriannn.store.order.entity.Order;
import com.adrrriannn.store.order.entity.OrderItem;
import com.adrrriannn.store.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private OrderItemMapper orderItemMapper;
    private UserService userService;

    @Autowired
    public OrderMapper(OrderItemMapper orderItemMapper,
                      UserService userService) {
        this.orderItemMapper = orderItemMapper;
        this.userService = userService;
    }

    public OrderDto map(Order order) {
        if(order == null) {
            return OrderDto.builder().build();
        }

        List<OrderItemDto> items = order.getItems().stream()
                .map(orderItemMapper::map)
                .collect(Collectors.toList());

        return OrderDto.builder()
                .id(order.getId())
                .items(items)
                .user(userService.getUser(order.getUserId()))
                .build();
    }

    public Order map(OrderDto orderDto) {

        List<OrderItem> items = orderDto.getItems().stream()
                .map(orderItemMapper::map)
                .collect(Collectors.toList());

        return Order.builder()
                .id(orderDto.getId())
                .items(items)
                .userId(orderDto.getUser().getId())
                .build();
    }
}
