package com.ku.blocking.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Taylan Erden
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(value = "spring.data.redis")
public class RedisProperties {

    /**
     * Redis hostname
     */
    private String hostname;

    /**
     * Redis port
     */
    private int port;


    /**
     * Redis password
     */
    private String password;
}