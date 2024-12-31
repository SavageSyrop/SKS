package ru.sks;

public class ShortUrl {
    private String originalUrl;
    private String shortUrl;
    private String creatorUUID;
    private int usagesLeft;
    private long validUntil;

    public ShortUrl(String originalUrl, String shortUrl, String creatorUUID, int usagesLeft, long validUntil) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.creatorUUID = creatorUUID;
        this.usagesLeft = usagesLeft;
        this.validUntil = validUntil;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getCreatorUUID() {
        return creatorUUID;
    }

    public void setCreatorUUID(String creatorUUID) {
        this.creatorUUID = creatorUUID;
    }

    public int getUsagesLeft() {
        return usagesLeft;
    }

    public void setUsagesLeft(int usagesLeft) {
        this.usagesLeft = usagesLeft;
    }

    public long getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(long validUntil) {
        this.validUntil = validUntil;
    }
}