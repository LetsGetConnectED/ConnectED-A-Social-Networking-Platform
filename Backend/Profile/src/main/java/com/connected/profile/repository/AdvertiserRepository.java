package com.connected.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.profile.model.Advertiser;

@Repository
public interface AdvertiserRepository extends JpaRepository<Advertiser,Long> {

	Advertiser findByEmail(String email);
}