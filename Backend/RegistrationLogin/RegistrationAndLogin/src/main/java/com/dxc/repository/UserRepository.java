
package com.dxc.repository;

import com.dxc.model.Role;
import com.dxc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.useremail = :email")
  Optional<User> findByUserEmail(@Param("email") String email);
    
    User findByRole(Role role);
}
