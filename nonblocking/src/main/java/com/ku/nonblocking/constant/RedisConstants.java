package com.ku.nonblocking.constant;

public enum RedisConstants {

    USER_CACHE("NONBLOCKING_USER_CACHE");

    private String cacheName;

    RedisConstants(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return cacheName;
    }
}
