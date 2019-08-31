package com.ku.blocking.repository;

import com.ku.blocking.constant.RedisConstants;
import com.ku.blocking.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RedisUserRepository implements UserRepository {

    private final RedisOperations<String, Object> redisOperations;

    @Override
    public User getUser(String id) {
        return (User) redisOperations.opsForHash().get(RedisConstants.USER_CACHE.getCacheName(), id);
    }

    @Override
    public List<User> getUsers(List<Object> ids) {
        return redisOperations.opsForHash().multiGet(RedisConstants.USER_CACHE.getCacheName(), ids)
                .stream()
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsers() {
        return redisOperations.opsForHash().values(RedisConstants.USER_CACHE.getCacheName())
                .stream()
                .map(User.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public void addUser(User user) {
        redisOperations.opsForHash().put(RedisConstants.USER_CACHE.getCacheName(), user.getId(), user);
    }

    @Override
    public void addUserIfAbsent(User user) {
        redisOperations.opsForHash().putIfAbsent(RedisConstants.USER_CACHE.getCacheName(), user.getId(), user);
    }

    @Override
    public void addUsers(List<User> users) {
        users.forEach(user -> redisOperations.opsForHash().put(RedisConstants.USER_CACHE.getCacheName(), user.getId(), user));
    }

    @Override
    public void addUsersIfAbsent(List<User> users) {
        users.forEach(user -> redisOperations.opsForHash().putIfAbsent(RedisConstants.USER_CACHE.getCacheName(), user.getId(), user));
    }

    @Override
    public void deleteUser(String id) {
        redisOperations.opsForHash().delete(RedisConstants.USER_CACHE.getCacheName(), id);
    }

    @Override
    public void deleteUsers(List<Object> ids) {
        redisOperations.opsForHash().delete(RedisConstants.USER_CACHE.getCacheName(), ids.toArray());
    }

    @Override
    public void deleteAllUsers() {
        redisOperations.delete(RedisConstants.USER_CACHE.getCacheName());
    }
}