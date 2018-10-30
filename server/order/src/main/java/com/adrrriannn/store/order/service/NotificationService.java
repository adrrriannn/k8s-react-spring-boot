package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.OrderDto;

public interface NotificationService {

    void sendNotification(OrderDto orderDto);
}
