package com.adrrriannn.store.auth.mapper;

import com.adrrriannn.store.auth.entity.User;
import com.adrrriannn.store.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto map(User user) {
        if(user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .surname(user.getSurname())
                .build();
    }
}
