package com.ku.nonblocking.repository;

import com.ku.nonblocking.constant.RedisConstants;
import com.ku.nonblocking.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RedisUserRepository implements UserRepository {

    private final ReactiveRedisOperations<String, Object> reactiveRedisOperations;


    @Override
    public Mono<User> getUser(String id) {
        return reactiveRedisOperations.opsForHash().get(RedisConstants.USER_CACHE.getCacheName(), id)
                .map(User.class::cast);
    }

    @Override
    public Flux<User> getUsers(List<Object> ids) {
        return reactiveRedisOperations.opsForHash().multiGet(RedisConstants.USER_CACHE.getCacheName(), ids)
                .flatMapIterable(objects -> objects)
                .map(User.class::cast);
    }

    @Override
    public Flux<User> getAllUsers() {
        return reactiveRedisOperations.opsForHash().values(RedisConstants.USER_CACHE.getCacheName())
                .map(User.class::cast);
    }

    @Override
    public Mono<Void> addUser(User user) {
        return reactiveRedisOperations.opsForHash().put(RedisConstants.USER_CACHE.getCacheName(), user.getId(), user)
                .then();
    }

    @Override
    public Mono<Void> addUserIfAbsent(User user) {
        return reactiveRedisOperations.opsForHash().putIfAbsent(RedisConstants.USER_CACHE.getCacheName(), user.getId(), user)
                .then();
    }

    @Override
    public Mono<Void> addUsers(List<User> users) {
        return Flux.fromIterable(users)
                .flatMap(user -> reactiveRedisOperations.opsForHash().put(RedisConstants.USER_CACHE.getCacheName(), user.getId(), user))
                .then();
    }

    @Override
    public Mono<Void> addUsersIfAbsent(List<User> users) {
        return Flux.fromIterable(users)
                .flatMap(user -> reactiveRedisOperations.opsForHash().putIfAbsent(RedisConstants.USER_CACHE.getCacheName(), user.getId(), user))
                .then();
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return reactiveRedisOperations.opsForHash().remove(RedisConstants.USER_CACHE.getCacheName(), id)
                .then();
    }

    @Override
    public Mono<Void> deleteUsers(List<Object> ids) {
        return reactiveRedisOperations.opsForHash().remove(RedisConstants.USER_CACHE.getCacheName(), ids.toArray())
                .then();
    }

    @Override
    public Mono<Void> deleteAllUsers() {
        return reactiveRedisOperations.opsForHash().delete(RedisConstants.USER_CACHE.getCacheName())
                .then();
    }
}