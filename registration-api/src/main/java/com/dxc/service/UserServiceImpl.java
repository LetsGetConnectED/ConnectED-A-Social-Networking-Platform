package com.dxc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.exception.IncorrectPinException;
import com.dxc.exception.UserExistsException;
import com.dxc.exception.UserNotFoundException;
import com.dxc.model.User;
import com.dxc.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepository;

	public User registerUser(User user) throws UserExistsException {
		final boolean existById = this.userRepository.existsByUsername(user.username);
		if (existById) {
			throw new UserExistsException("User Already Exists");
		}
		return this.userRepository.save(user);
	}

	@Override
	public User authenticateUser(String username, String password)
			throws UserNotFoundException, IncorrectPinException {
//		final Optional<User> optionalUser = this.userRepository.findByUsernameAndPassword(username, password);
		final Optional<User> optionalUser = this.userRepository.findByUsername(username);
		System.out.println(optionalUser.toString());
		System.out.println("hello");
		
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("User Not Found");
		}
		System.out.println("test:" + optionalUser.toString());
		return optionalUser.get();
	}

}
