package ru.sks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
public class ManagerImpl implements Manager {
    private final Map<String, List<ShortUrl>> userToUrlsMap;

    private String currentUserUUID;

    @Autowired
    private Configuration configuration;

    public ManagerImpl() {
        this.userToUrlsMap = new HashMap<>();
        this.currentUserUUID = null;
    }


    @Override
    public void deleteUrlByShortUrl(String shortUrlToDelete) throws Exception {
        ShortUrl url = findUrlByShortUrl(shortUrlToDelete);
        if (!this.isOwnerOfUrl(url, this.currentUserUUID)) {
            throw new Exception("Только владелец может удалить ссылку");
        } else {
            this.userToUrlsMap.get(this.currentUserUUID).remove(url);
        }
    }

    @Override
    public ShortUrl createUrl(String longUrl, long usagesCount, long validUntilInSeconds) throws Exception {
        if (this.currentUserUUID == null) {
            System.out.println("Вам был выдан UUID: " + register() + ". Используйте его для дальнейшего взаимодействия с созданными ссылками");
        }
        String shortUrl = configuration.getBaseUrl() + UUID.randomUUID();
        ShortUrl newShortUrl = new ShortUrl(longUrl, shortUrl, this.currentUserUUID, Math.max(usagesCount, configuration.getMinUsages()), Math.min(validUntilInSeconds, Instant.now().getEpochSecond() + configuration.getMaxUrlLifetime()));
        List<ShortUrl> list = this.userToUrlsMap.get(this.currentUserUUID);
        list.add(newShortUrl);
        return newShortUrl;
    }

    @Override
    public ShortUrl findUrlByShortUrl(String shortUrl) throws Exception {
        for (Map.Entry<String, List<ShortUrl>> entry : userToUrlsMap.entrySet())
            for (ShortUrl url : entry.getValue()) {
                if (Objects.equals(url.getShortUrl(), shortUrl)) {
                    return url;
                }
            }
        throw new Exception("Такой короткой ссылки нет");
    }

    @Override
    public ShortUrl useShortUrl(String shortUrl) throws Exception {
        ShortUrl url = findUrlByShortUrl(shortUrl);

        if (isUrlTimeValid(url) && url.getUsagesLeft() > 0) {
            url.setUsagesLeft(url.getUsagesLeft() - 1);
            return url;
        } else {
            this.userToUrlsMap.get(url.getCreatorUUID()).remove(url);
            throw new Exception("Эта ссылка больше недоступна");
        }
    }

    @Override
    public ShortUrl editUrlUsageLimit(String shortUrl, long newUsagesCount) throws Exception {
        ShortUrl url = findUrlByShortUrl(shortUrl);
        if (!this.isOwnerOfUrl(url, this.currentUserUUID)) {
            throw new Exception("Только владелец может изменить количество использований ссылки");
        } else {
            url.setUsagesLeft(newUsagesCount);
        }
        return url;
    }

    @Override
    public ShortUrl editUrlTimeLimit(String shortUrl, long urlTimeLimit) throws Exception {
        ShortUrl url = findUrlByShortUrl(shortUrl);
        if (!this.isOwnerOfUrl(url, this.currentUserUUID)) {
            throw new Exception("Только владелец может изменить время жизни ссылки");
        } else {
            url.setValidUntil(urlTimeLimit);
        }
        return url;
    }

    @Override
    public void login(String uuid) throws Exception {
        if (userToUrlsMap.get(uuid)==null) {
            throw new Exception("Позователя с таким UUID нет");
        }
        this.currentUserUUID = uuid;
    }

    @Override
    public void logout() throws Exception {
        if (this.currentUserUUID == null) {
            throw new Exception("Вы не авторизованы");
        }
        this.currentUserUUID = null;
    }

    @Override
    public String register() throws Exception {
        if (currentUserUUID != null) {
            throw new Exception("Вы уже авторизованы!");
        }
        this.currentUserUUID = UUID.randomUUID().toString();
        this.userToUrlsMap.put(this.currentUserUUID, new ArrayList<>());
        return currentUserUUID;
    }

    @Override
    public String whoAmI() {
        return this.currentUserUUID;
    }

    @Override
    public List<ShortUrl> getUrlByCreatorUUID() throws Exception {
        List<ShortUrl> list = userToUrlsMap.get(this.currentUserUUID);
        if (list == null || list.isEmpty()) {
            throw new Exception("Список ссылок пуст");
        } else {
            list.removeIf(url -> !isUrlTimeValid(url) || url.getUsagesLeft() <= 0);
        }
        return list;
    }

    private boolean isUrlTimeValid(ShortUrl url) {
        return url.getValidUntil() - Instant.now().getEpochSecond() >= 0;
    }
}
