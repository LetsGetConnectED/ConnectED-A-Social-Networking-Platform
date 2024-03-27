package com.connected.connection.Service;

import com.connected.connection.Repository.UserRepository;
import com.connected.connection.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	@Override
	public User updateUserByUsername(String username, User user) {
		User existingUser = userRepository.findByUsername(username);
		if (existingUser != null) {
			existingUser.setName(user.getName());
			existingUser.setPassword(user.getPassword());
			return userRepository.save(existingUser);
		}
		return null;
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteUserByUsername(String username) {
		User existingUser = userRepository.findByUsername(username);
		if (existingUser != null) {
			userRepository.delete(existingUser);
		}
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
}
