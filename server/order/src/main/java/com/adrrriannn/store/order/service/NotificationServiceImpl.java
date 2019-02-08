package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    private MessageChannel messageChannel;
    private String orderNotificationTopic;

    @Autowired
    public NotificationServiceImpl(@Lazy @Qualifier("producingChannel") MessageChannel messageChannel,
                                   @Value("${kafka.topic.order-notification}") String orderNotificationTopic) {
        this.messageChannel = messageChannel;
        this.orderNotificationTopic = orderNotificationTopic;
    }

    @Override
    public void sendNotification(OrderDto orderDto) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, orderNotificationTopic);
        MessageHeaders messageHeaders = new MessageHeaders(headers);

        Message<OrderDto> message = MessageBuilder.createMessage(orderDto, messageHeaders);
        messageChannel.send(message);
    }
}
