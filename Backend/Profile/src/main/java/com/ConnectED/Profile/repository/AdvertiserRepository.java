package com.ConnectED.Profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ConnectED.Profile.model.Advertiser;
@Repository
public interface AdvertiserRepository extends JpaRepository<Advertiser,Long> {

	Advertiser findByEmail(String email);
}
