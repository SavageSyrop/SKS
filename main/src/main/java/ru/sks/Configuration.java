package ru.sks;

import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${maxUrlLifetime}")
    private long maxUrlLifetime;

    @Value("${minUsages}")
    private long minUsages;

    @Value("${baseURL}")
    private String baseUrl;

    public long getMaxUrlLifetime() {
        return maxUrlLifetime;
    }

    public long getMinUsages() {
        return minUsages;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
