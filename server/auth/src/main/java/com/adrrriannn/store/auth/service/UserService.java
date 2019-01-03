package com.adrrriannn.store.auth.service;

import com.adrrriannn.store.auth.dto.UserSignIn;
import com.adrrriannn.store.dto.UserDto;

public interface UserService {

    UserDto getUserById(String id);
    UserDto getUserByEmailAndPassword(String email, String password);
    UserDto createUser(UserSignIn userSignIn);
}
