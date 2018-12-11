package com.adrrriannn.store.user.mapper;

import com.adrrriannn.store.dto.UserDto;
import com.adrrriannn.store.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto map(User user) {
        if(user == null) {
            return UserDto.builder().build();
        }

        return UserDto.builder()
//                .id(auth.getId())
//                .email(auth.getEmail())
                .build();
    }

    public User map(UserDto userDto) {
        return User.builder()
//                .id(orderDto.getId())
//                .email(orderDto.getEmail())
                .build();
    }
}
