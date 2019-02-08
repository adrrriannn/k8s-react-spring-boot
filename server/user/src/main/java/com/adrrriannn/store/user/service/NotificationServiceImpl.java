package com.adrrriannn.store.user.service;

import com.adrrriannn.store.dto.UserDto;
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

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    private MessageChannel messageChannel;
    private String userNotificationTopic;

    @Autowired
    public NotificationServiceImpl(@Lazy @Qualifier("producingChannel") MessageChannel messageChannel,
                                   @Value("${kafka.topic.auth-notification}") String userNotificationTopic) {
        this.messageChannel = messageChannel;
        this.userNotificationTopic = userNotificationTopic;
    }

    @Override
    public void sendNotification(UserDto userDto) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, userNotificationTopic);
        MessageHeaders messageHeaders = new MessageHeaders(headers);

        Message<UserDto> message = MessageBuilder.createMessage(userDto, messageHeaders);
        messageChannel.send(message);
    }
}
