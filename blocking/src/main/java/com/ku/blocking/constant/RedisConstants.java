package com.ku.blocking.constant;

public enum RedisConstants {

    USER_CACHE("BLOCKING_USER_CACHE");

    private String cacheName;

    RedisConstants(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return cacheName;
    }
}
