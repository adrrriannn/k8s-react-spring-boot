package com.adrrriannn.store.notification.service;

import com.adrrriannn.store.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogNotificationService implements NotificationService {

    @Override
    public void sendNotification(OrderDto orderDto) {
        log.info("Notification generated for Order: {}", orderDto);
    }
}
