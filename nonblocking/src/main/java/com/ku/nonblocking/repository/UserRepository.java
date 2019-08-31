package com.ku.nonblocking.repository;

import com.ku.nonblocking.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserRepository {

    Mono<User> getUser(String id);

    Flux<User> getUsers(List<Object> ids);

    Flux<User> getAllUsers();

    Mono<Void> addUser(User user);

    Mono<Void> addUserIfAbsent(User user);

    Mono<Void> addUsers(List<User> users);

    Mono<Void> addUsersIfAbsent(List<User> users);

    Mono<Void> deleteUser(String id);

    Mono<Void> deleteUsers(List<Object> ids);

    Mono<Void> deleteAllUsers();
}
