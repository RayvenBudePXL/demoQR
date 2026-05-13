package com.example.demoqr;

import org.springframework.stereotype.Service;

@Service
public class DynamicQrService {

    private final QrService qrService;
    private final TokenService tokenService;
    private final QrProperties qrProperties;

    public DynamicQrService(QrService qrService, TokenService tokenService, QrProperties qrProperties) {
        this.qrService = qrService;
        this.tokenService = tokenService;
        this.qrProperties = qrProperties;
    }

    public byte[] generateQrPng() {
        String token = tokenService.createToken();
        String accessUrl = buildAccessUrl(token);
        return qrService.generateQrPng(accessUrl);
    }

    public boolean isTokenValid(String token) {
        return tokenService.isValid(token);
    }

    private String buildAccessUrl(String token) {
        return qrProperties.getBaseUrl() + "/qr/access?token=" + token;
    }
}
