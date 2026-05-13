package com.example.demoqr;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    private Map<String, Long> tokens = new HashMap<>();

    // maak token (geldig 30 sec)
    public String createToken() {
        String token = UUID.randomUUID().toString();
        long expiry = System.currentTimeMillis() + 20000;

        tokens.put(token, expiry);
        return token;
    }

    // check token
    public boolean isValid(String token) {
        Long expiry = tokens.get(token);

        if (expiry == null) return false;

        if (System.currentTimeMillis() > expiry) {
            tokens.remove(token);
            return false;
        }

        tokens.remove(token); // one-time use
        return true;
    }
}