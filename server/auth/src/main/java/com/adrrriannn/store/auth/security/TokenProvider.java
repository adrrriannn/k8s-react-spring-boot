package com.adrrriannn.store.auth.security;

import com.adrrriannn.store.auth.dto.UserLogin;

public interface TokenProvider {

    String getTokenForCredentials(UserLogin userLogin);
}
