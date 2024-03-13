package com.connected.appchatmicro.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.connected.appchatmicro.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}