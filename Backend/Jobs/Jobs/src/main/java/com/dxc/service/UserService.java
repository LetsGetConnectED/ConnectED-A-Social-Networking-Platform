package com.dxc.service;

import com.dxc.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);
}
