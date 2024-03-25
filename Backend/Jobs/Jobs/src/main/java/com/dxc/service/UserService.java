package com.dxc.service;

import com.dxc.model.User;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);

}
