package com.connected.advertisement.repo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.advertisement.model.AdvertisementPost;

@Repository
public interface AdvertisementPostRepository extends JpaRepository<AdvertisementPost, Long> {
	
	 List<AdvertisementPost> findAllByEmail(String email);

	    void deleteByEmailAndPostDate(String email, LocalDate postDate);
	    void deleteAllByEmail(String email);
	    Optional<AdvertisementPost> findByEmailAndPostDate(String email, LocalDate postDate);

		
}
