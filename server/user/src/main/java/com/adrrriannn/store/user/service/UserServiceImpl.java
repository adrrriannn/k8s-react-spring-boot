package com.adrrriannn.store.user.service;

import com.adrrriannn.store.dto.UserDto;
import com.adrrriannn.store.user.entity.User;
import com.adrrriannn.store.user.mapper.UserMapper;
import com.adrrriannn.store.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private NotificationService notificationService;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           NotificationService notificationService,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.map(userDto);
        user = userRepository.save(user);
        userDto = userMapper.map(user);

        notificationService.sendNotification(userDto);

        return userDto;
    }
}
