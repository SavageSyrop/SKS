package ru.sks;

public interface Manager {
    boolean isUrlTimeValid(long urlLifeLimit);

    void deleteUrl();

    ShortUrl createUrl();

    ShortUrl findUrlByShortUrl();

    ShortUrl editUrlLimit();

    default boolean isOwnerOfUrl(ShortUrl url, String uuid) {
        return uuid.equals(url.getCreatorUUID());
    }

    void login();

    void logout();

    String register();

    String whoAmI();
}
