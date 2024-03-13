package com.dxc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.exception.IncorrectPinException;
import com.dxc.exception.UserExistsException;
import com.dxc.exception.UserNotFoundException;
import com.dxc.model.User;
import com.dxc.model.UserCred;
import com.dxc.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
//	@PostMapping("/register")
//	public User registerUser(@RequestBody User user) throws UserExistsException {
//		return this.service.registerUser(user);
//	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody User user) {
		try {
			return this.service.registerUser(user).toString();			
		} catch (Exception UserExistsException) {
			return "UserName Already Exists!!";
		}
	}
	
	@GetMapping("/login")
	public String authenticateUser(@RequestBody UserCred credentails) throws UserNotFoundException, IncorrectPinException{
		try {
			final User user = this.service.authenticateUser(credentails.getUsername(), credentails.getPassword());
			return user.toString();		
		} catch (Exception UserNotFoundException) {
			return "User Not Found!!";
		}
	}
	
}