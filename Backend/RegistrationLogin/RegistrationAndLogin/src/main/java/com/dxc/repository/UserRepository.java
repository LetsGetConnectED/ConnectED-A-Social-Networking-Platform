// UserRepository.java
package com.dxc.repository;

import com.dxc.model.Role;
import com.dxc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUseremail(String email);
    
    User findByRole(Role role);
}
