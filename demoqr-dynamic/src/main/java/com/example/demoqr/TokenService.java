package com.example.demoqr;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {

    private final Map<String, Long> tokens = new ConcurrentHashMap<>();
    private final QrProperties qrProperties;

    public TokenService(QrProperties qrProperties) {
        this.qrProperties = qrProperties;
    }

    public String createToken() {
        String token = UUID.randomUUID().toString();
        long expiry = System.currentTimeMillis() + qrProperties.getTokenLifetimeMs();

        tokens.put(token, expiry);
        return token;
    }

    public boolean isValid(String token) {
        Long expiry = tokens.get(token);

        if (expiry == null) return false;

        if (System.currentTimeMillis() > expiry) {
            tokens.remove(token);
            return false;
        }

        tokens.remove(token);
        return true;
    }
}
