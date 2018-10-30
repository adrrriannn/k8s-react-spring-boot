package com.adrrriannn.store.notification.service;

import com.adrrriannn.store.dto.OrderDto;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

public class OrderMessageHandler implements MessageHandler {

    private NotificationService notificationService;

    public OrderMessageHandler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        Object object = message.getPayload();


        notificationService.sendNotification((OrderDto) object);
    }
}
