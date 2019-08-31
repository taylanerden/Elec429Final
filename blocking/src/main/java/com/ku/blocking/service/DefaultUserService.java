package com.ku.blocking.service;

import com.ku.blocking.domain.User;
import com.ku.blocking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUser(String id) {
        return userRepository.getUser(id);
    }

    @Override
    public List<User> getUsers(List<Object> ids) {
        return userRepository.getUsers(ids);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public void addUserIfAbsent(User user) {
        userRepository.addUserIfAbsent(user);
    }

    @Override
    public void addUsers(List<User> users) {
        userRepository.addUsers(users);
    }

    @Override
    public void addUsersIfAbsent(List<User> users) {
        userRepository.addUsersIfAbsent(users);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteUser(id);
    }

    @Override
    public void deleteUsers(List<Object> ids) {
        userRepository.deleteUsers(ids);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAllUsers();

    }
}
