package com.java.taskapi.service;

import com.java.taskapi.model.User;
import java.util.UUID;
import java.util.List;

public interface UserService {
    User register(User user);
    User login(String username);
    List<User> getAllUsers();
}