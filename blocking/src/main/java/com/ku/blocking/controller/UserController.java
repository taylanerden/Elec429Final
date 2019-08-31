package com.ku.blocking.controller;

import com.ku.blocking.domain.User;
import com.ku.blocking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/get-user")
    public User getUser(@RequestParam(value = "user-id") String id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "get-users")
    public List<User> getUsers(@RequestParam(value = "user-ids") List<Object> ids) {
        return userService.getUsers(ids);
    }

    @PostMapping(value = "get-all-users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping(value = "/add-user")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping(value = "/add-user-if-absent")
    public void addUserIfAbsent(@RequestBody User user) {
        userService.addUserIfAbsent(user);
    }

    @PutMapping(value = "/add-users")
    public void addUsers(@RequestBody List<User> users) {
        userService.addUsers(users);
    }

    @PutMapping(value = "/add-users-if-absent")
    public void addUsersIfAbsent(@RequestBody List<User> users) {
        userService.addUsersIfAbsent(users);
    }

    @DeleteMapping(value = "/delete-user")
    public void deleteUser(@RequestParam(value = "user-id") String id) {
        userService.deleteUser(id);
    }

    @DeleteMapping(value = "/delete-users")
    public void deleteUsers(@RequestParam(value = "user-ids") List<Object> ids) {
        userService.deleteUsers(ids);
    }

    @DeleteMapping(value = "/delete-all-users")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }

    @GetMapping(value = "/create-user-group")
    public void createUserGroup() {
        List<User> users;
    }

}
