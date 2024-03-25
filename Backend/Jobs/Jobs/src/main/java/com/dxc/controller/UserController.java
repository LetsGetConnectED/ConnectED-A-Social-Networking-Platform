package com.dxc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dxc.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

import com.dxc.dto.UserDTO;
import com.dxc.model.User;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setUseremail(userDTO.getUseremail());
        user.setUserpassword(userDTO.getUserpassword());
        Optional<User> existingUser = userService.getUserByEmail(user.getUseremail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User with this email already exists");
        }

        userService.saveUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable Long userid) {
        Optional<User> userOptional = userService.getUserById(userid);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }

    @GetMapping("/email/{useremail}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String useremail) {
        Optional<User> userOptional = userService.getUserByEmail(useremail);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }
    }
}
