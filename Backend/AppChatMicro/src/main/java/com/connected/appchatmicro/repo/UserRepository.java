package com.connected.appchatmicro.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.connected.appchatmicro.model.User;


@Repository
public interface UserRepository extends JpaRepository <User,Long> {
    @Query(value = "select * from User", nativeQuery = true)
    Optional<List<User>> getAll();

    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(Long userId);
    Optional<User> findByEmail(String email);


    @Query(value = "select * from User where username like %:param% or first_name like %:param% or last_name like %:param% ", nativeQuery = true)
    Optional<List<User>> getUsersByQuery(@Param("param") String query_input);



}

