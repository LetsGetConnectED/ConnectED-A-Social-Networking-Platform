package com.app.add.Repository;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

import com.app.add.Model.Advertisement;

	@Repository
	public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
	}


