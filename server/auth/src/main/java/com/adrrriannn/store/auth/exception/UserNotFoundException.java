package com.adrrriannn.store.auth.exception;

public class UserNotFoundException extends RuntimeException {

    private static String NOT_FOUND_BY_EMAIL_MESSAGE = "User not found for email: %s";

    private UserNotFoundException(String message) {
        super(message);
    }

    public static UserNotFoundException byEmail(String email) {
        return new UserNotFoundException(String.format(NOT_FOUND_BY_EMAIL_MESSAGE, email));
    }
}
