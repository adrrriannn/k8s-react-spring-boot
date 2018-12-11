package com.adrrriannn.store.user.service;

import com.adrrriannn.store.dto.UserDto;

public interface NotificationService {

    void sendNotification(UserDto userDto);
}
