package com.adrrriannn.store.auth.service;

import com.adrrriannn.store.auth.dto.UserSignIn;
import com.adrrriannn.store.auth.entity.User;
import com.adrrriannn.store.auth.mapper.UserMapper;
import com.adrrriannn.store.auth.repository.UserRepository;
import com.adrrriannn.store.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);

        return userMapper.map(user);
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user == null || !bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return userMapper.map(user);
    }

    @Override
    public UserDto createUser(UserSignIn userSignIn) {

        User user = User.builder()
                .email(userSignIn.getEmail())
                .password(bCryptPasswordEncoder.encode(userSignIn.getPassword()))
                .firstname(userSignIn.getFirstname())
                .surname(userSignIn.getSurname())
                .build();
        user = userRepository.save(user);

        return userMapper.map(user);
    }

}
