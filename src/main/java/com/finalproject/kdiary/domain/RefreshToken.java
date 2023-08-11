package com.finalproject.kdiary.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash(value = "refreshToken", timeToLive = 60)
public class RefreshToken {
    @Id
    private String refreshToken;
    private final String userId;

    public RefreshToken(final String refreshToken, final String userId) {
        this.refreshToken = refreshToken;
        this.userId = userId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUserId() {
        return userId;
    }
}
