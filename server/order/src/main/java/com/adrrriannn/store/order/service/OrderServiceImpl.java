package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.OrderDto;
import com.adrrriannn.store.order.entity.Order;
import com.adrrriannn.store.order.mapper.OrderMapper;
import com.adrrriannn.store.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    private NotificationService notificationService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.notificationService = notificationService;
    }

    @Override
    public OrderDto processOrder(OrderDto orderDto) {
        Order order = orderMapper.map(orderDto);
        order = orderRepository.save(order);

        orderDto = orderMapper.map(order);

        notificationService.sendNotification(orderDto);

        return orderDto;
    }

    @Override
    public OrderDto getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);

        return orderMapper.map(
                order
                    .orElseThrow(() -> new RuntimeException("Order not found for id: " + id))
        );
    }
}
