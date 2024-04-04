package com.connected.userpost.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connected.userpost.Model.User;



public interface UserRepository extends JpaRepository<User,Integer> {
	 Optional<User> findByEmail(String email);

	//User findUserByEmail(String email);

}
