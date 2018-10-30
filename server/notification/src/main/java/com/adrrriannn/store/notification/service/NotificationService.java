package com.adrrriannn.store.notification.service;

import com.adrrriannn.store.dto.OrderDto;

public interface NotificationService {
    void sendNotification(OrderDto orderDto);
}
