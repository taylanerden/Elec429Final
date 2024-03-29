package com.ku.blocking.repository;

import com.ku.blocking.domain.User;

import java.util.List;

public interface UserRepository {

    User getUser(String id);

    List<User> getUsers(List<Object> ids);

    List<User> getAllUsers();

    void addUser(User user);

    void addUserIfAbsent(User user);

    void addUsers(List<User> users);

    void addUsersIfAbsent(List<User> users);

    void deleteUser(String id);

    void deleteUsers(List<Object> ids);

    void deleteAllUsers();
}
