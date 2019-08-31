package com.ku.nonblocking.service;

import com.ku.nonblocking.domain.User;
import com.ku.nonblocking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<User> getUser(String id) {
        return userRepository.getUser(id);
    }

    @Override
    public Flux<User> getUsers(List<Object> ids) {
        return userRepository.getUsers(ids);
    }

    @Override
    public Flux<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public Mono<Void> addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public Mono<Void> addUserIfAbsent(User user) {
        return userRepository.addUserIfAbsent(user);
    }

    @Override
    public Mono<Void> addUsers(List<User> users) {
        return userRepository.addUsers(users);
    }

    @Override
    public Mono<Void> addUsersIfAbsent(List<User> users) {
        return userRepository.addUsersIfAbsent(users);
    }

    @Override
    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteUser(id);
    }

    @Override
    public Mono<Void> deleteUsers(List<Object> ids) {
        return userRepository.deleteUsers(ids);
    }

    @Override
    public Mono<Void> deleteAllUsers() {
        return userRepository.deleteAllUsers();
    }
}
