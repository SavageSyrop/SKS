package ru.sks;

import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private final Map<String, List<ShortUrl>> userToUrlsMap;

    private String currentUserUUID;

    @Value("${maxUrlLifetime}")
    private long maxUrlLifetime;

    @Value("${baseURL}")
    private String baseUrl;

    public ManagerImpl() {
        this.userToUrlsMap = new HashMap<>();
        this.currentUserUUID = null;
    }

    @Override
    public boolean isUrlTimeValid(long urlLifeLimit) {
        return false;
    }

    @Override
    public void deleteUrl() {

    }

    @Override
    public ShortUrl createUrl() {
        return null;
    }

    @Override
    public ShortUrl findUrlByShortUrl() {
        return null;
    }

    @Override
    public ShortUrl editUrlLimit() {
        return null;
    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }

    @Override
    public String register() {
        return null;
    }

    @Override
    public String whoAmI() {
        return null;
    }
}
