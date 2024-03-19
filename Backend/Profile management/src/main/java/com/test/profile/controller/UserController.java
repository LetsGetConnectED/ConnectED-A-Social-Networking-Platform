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
    public ResponseEntity<User> saveUser(@RequestBody User email) {
        User savedUser = userServiceImpl.saveUser(email);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);      //.body(savedUser);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        User user = userServiceImpl.getUser(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    
    @PutMapping("/{email}")
    public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody User newUser) {
        try {
            User updatedUser = userServiceImpl.putUser(email, newUser);
            return ResponseEntity.ok().body(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userServiceImpl.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
