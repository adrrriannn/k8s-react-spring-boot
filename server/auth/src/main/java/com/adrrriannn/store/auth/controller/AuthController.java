package com.adrrriannn.store.auth.controller;

import com.adrrriannn.store.auth.dto.UserLogin;
import com.adrrriannn.store.auth.dto.UserSignIn;
import com.adrrriannn.store.auth.exception.UserNotFoundException;
import com.adrrriannn.store.auth.security.TokenProvider;
import com.adrrriannn.store.auth.service.UserService;
import com.adrrriannn.store.auth.validator.UserValidator;
import com.adrrriannn.store.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class AuthController {

    private UserService userService;
    private UserValidator userValidator;

    private TokenProvider tokenProvider;

    @Autowired
    public AuthController(UserService userService,
                          UserValidator userValidator,
                          TokenProvider tokenProvider) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    public Object signIn(@RequestBody @Valid UserSignIn userSignIn) {

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.createUser(userSignIn);
        response.put("user", userDto);
        response.put("token", tokenProvider.getTokenForCredentials(userSignIn));

        return response;
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Valid UserLogin userLogin) {
        Map<String, Object> response = new HashMap<>();

        String email = userLogin.getEmail();
        String password = userLogin.getPassword();

        UserDto userDto = userService.getUserByEmailAndPassword(email, password);
        if(userDto == null) {
            throw UserNotFoundException.byEmail(email);
        }
        response.put("user", userDto);
        response.put("token", tokenProvider.getTokenForCredentials(userLogin));

        return response;
    }

    @GetMapping("/{id}")
    public Object getUser(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }
}
