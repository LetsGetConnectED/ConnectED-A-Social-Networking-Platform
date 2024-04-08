package com.connected.advertisement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connected.advertisement.model.User;



public interface UserRepository extends JpaRepository<User,Integer> {
	 Optional<User> findByEmail(String email);

	//User findUserByEmail(String email);

}
