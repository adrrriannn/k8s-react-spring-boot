package com.adrrriannn.store.notification.service;

import com.adrrriannn.store.dto.OrderDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageHandler {

    private NotificationService notificationService;

    public OrderMessageHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics="${kafka.topic.order-notification}")
    public void handleMessage(OrderDto orderDto) {
        notificationService.sendNotification(orderDto);
    }
}
