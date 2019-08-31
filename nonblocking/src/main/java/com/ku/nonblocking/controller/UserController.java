package com.ku.nonblocking.controller;

import com.ku.nonblocking.domain.User;
import com.ku.nonblocking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/get-user")
    public Mono<User> getUser(@RequestParam(value = "user-id") String id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "get-users")
    public Flux<User> getUsers(@RequestParam(value = "user-ids") List<Object> ids) {
        return userService.getUsers(ids);
    }

    @PostMapping(value = "get-all-users")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping(value = "/add-user")
    public Mono<Void> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping(value = "/add-user-if-absent")
    public Mono<Void> addUserIfAbsent(@RequestBody User user) {
        return userService.addUserIfAbsent(user);
    }

    @PutMapping(value = "/add-users")
    public Mono<Void> addUsers(@RequestBody List<User> users) {
        return userService.addUsers(users);
    }

    @PutMapping(value = "/add-users-if-absent")
    public Mono<Void> addUsersIfAbsent(@RequestBody List<User> users) {
        return userService.addUsersIfAbsent(users);
    }

    @DeleteMapping(value = "/delete-user")
    public Mono<Void> deleteUser(@RequestParam(value = "user-id") String id) {
        return userService.deleteUser(id);
    }

    @DeleteMapping(value = "/delete-users")
    public Mono<Void> deleteUsers(@RequestParam(value = "user-ids") List<Object> ids) {
        return userService.deleteUsers(ids);
    }

    @DeleteMapping(value = "/delete-all-users")
    public Mono<Void> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

}
