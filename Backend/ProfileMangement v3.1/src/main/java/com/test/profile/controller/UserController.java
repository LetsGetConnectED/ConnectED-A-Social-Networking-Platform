package com.test.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.test.profile.entity.User;
import com.test.profile.exception.ResourceNotFoundException;
import com.test.profile.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User savedUser = userServiceImpl.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);      //.body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userServiceImpl.getUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id,User user) {
//        User updatedUser = userServiceImpl.putUser(id, user);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User newUser) {
        try {
            User updatedUser = userServiceImpl.putUser(userId, newUser);
            return ResponseEntity.ok().body(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
