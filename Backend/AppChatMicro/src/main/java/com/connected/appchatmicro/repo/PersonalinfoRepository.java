package com.connected.appchatmicro.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.connected.appchatmicro.model.Personalinfo;

import java.util.Optional;

public interface PersonalinfoRepository extends JpaRepository<Personalinfo,Long> {

    Optional<Personalinfo> findByUserUserId(Long userId);


}
