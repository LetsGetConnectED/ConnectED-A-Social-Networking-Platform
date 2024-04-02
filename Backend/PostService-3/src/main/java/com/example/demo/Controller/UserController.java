package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer id) {
        try {
            User user = userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.registeredUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer userId) {
        try {
            User updatedUser = userService.updateUser(user, userId);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return new ResponseEntity<>("User not found with id " + userId, HttpStatus.NOT_FOUND);
        }
        userRepository.delete(user.get());
        return new ResponseEntity<>("User deleted successfully with id " + userId, HttpStatus.OK);
    }
    @GetMapping("/count/{userId}")
    public ResponseEntity<User> getUserWithPosts(@PathVariable("userId") int userId) {
        User user = userService.getUserWithPosts(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


//package com.example.demo.Controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.Model.User;
//import com.example.demo.Repository.UserRepository;
//import com.example.demo.Service.UserService;
//
//
//@RestController
//public class UserController {
//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	UserService userService;
//	
//	@GetMapping("/users")
//	public List<User> getUsers(){
//		List<User> users = userRepository.findAll();
//		return users;
//		
//	}
//	@GetMapping("/users/{userId}")
//	public User getUserById(@PathVariable("userId")Integer id) throws Exception {
//		
//		User user=userService.findUserById(id);
//		return user;
//		
//		
//	}
//	@PostMapping("/users")
//	public User createUser(@RequestBody User user) {
//		
//		
//		User savedUser = userService.registeredUser(user);
//		return savedUser;
//		
//		}
//	@PutMapping("/users/{userId}")
//	public User updateUser(@RequestBody User user,@PathVariable Integer userId) throws Exception {
//		User updatedUser = userService.updateUser(user, userId);
//		return updatedUser;
//		
//		
//	}
//	@DeleteMapping("users/{userId}")
//	public String deleteUser(@PathVariable("userId") Integer userId ) throws Exception {
//		Optional<User> user = userRepository.findById(userId);
//		if(user.isEmpty()) {
//			throw new Exception("user not exit with id"+userId);
//		}
//		userRepository.delete(user.get());
//		return "user deleted successfully with id "+ userId;
//		
//	}
//	
//	
//	
//
//}
