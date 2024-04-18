package com.connected.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.post.model.Advertiser;

@Repository
public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {
	// Find advertiser by email
	Optional<Advertiser> findByEmail(String email);
}
