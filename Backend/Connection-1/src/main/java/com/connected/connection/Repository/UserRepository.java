package com.connected.connection.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.connection.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

	
}


/*
 * package com.connected.connection.Repository;
 * 
 * import org.springframework.data.jpa.repository.JpaRepository; import
 * org.springframework.stereotype.Repository;
 * 
 * import com.connected.connection.model.User;
 * 
 * @Repository public interface UserRepository extends JpaRepository<User, Long>
 * { User findByUsername(String username);
 * 
 * User findByUsernameAndPassword(String username, String password);
 * 
 * }
 */