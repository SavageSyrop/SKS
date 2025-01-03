package ru.sks;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public interface Manager {

    void deleteUrlByShortUrl(String shortUrlToDelete) throws Exception;

    ShortUrl createUrl(String longUrl, long usagesCount, long validUntilInSeconds) throws Exception;

    ShortUrl findUrlByShortUrl(String shortUrl) throws Exception;

    ShortUrl useShortUrl(String shortUrl) throws Exception;

    ShortUrl editUrlUsageLimit(String shortUrl, long newUsagesCount) throws Exception;

    ShortUrl editUrlTimeLimit(String shortUrl, long urlTimeLimit) throws Exception;

    void login(String uuid) throws Exception;

    void logout() throws Exception;

    String register() throws Exception;

    String whoAmI();
    List<ShortUrl> getUrlByCreatorUUID() throws Exception;

    default boolean isOwnerOfUrl(ShortUrl url, String uuid) {
        return Objects.equals(url.getCreatorUUID(),uuid);
    }
}
