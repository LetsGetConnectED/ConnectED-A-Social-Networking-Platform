package com.dxc.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dxc.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByUsernameAndPassword(String username, String password);
	
//	@Query("select u.id,u.password,u.username from Users u where u.username=:username")
	
	Optional<User> findByUsername(String username);
	
	boolean existsByUsername(String userName);

}
