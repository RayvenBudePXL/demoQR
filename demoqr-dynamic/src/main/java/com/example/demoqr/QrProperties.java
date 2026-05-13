package com.example.demoqr;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.qr")
public class QrProperties {

    private String baseUrl;
    private long tokenLifetimeMs;
    private String frontendOrigin;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public long getTokenLifetimeMs() {
        return tokenLifetimeMs;
    }

    public void setTokenLifetimeMs(long tokenLifetimeMs) {
        this.tokenLifetimeMs = tokenLifetimeMs;
    }

    public String getFrontendOrigin() {
        return frontendOrigin;
    }

    public void setFrontendOrigin(String frontendOrigin) {
        this.frontendOrigin = frontendOrigin;
    }
}
