package com.adrrriannn.store.auth.security;
import com.adrrriannn.store.auth.dto.UserLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JWTTokenProvider implements TokenProvider {

    private long expirationTime;
    private String secret;

    @Autowired
    public JWTTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.expiration-time}") long expirationTime) {
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    @Override
    public String getTokenForCredentials(UserLogin userLogin) {
        final String base64Secret = Base64.getEncoder().encodeToString(secret.getBytes());
        return Jwts.builder()
                .setSubject(userLogin.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS512, base64Secret)
                .compact();
    }
}
