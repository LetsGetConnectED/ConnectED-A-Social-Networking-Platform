package com.connected.post.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.post.model.AdvertisementPost;
import com.connected.post.model.Advertiser;
import com.connected.post.model.AdvertiserComment;

import jakarta.transaction.Transactional;

@Repository
public interface AdvertisementPostRepository extends JpaRepository<AdvertisementPost, Long> {
	
	
	

    List<AdvertisementPost> findAllByAdvertiserEmail(String advertiserEmail);

    Optional<AdvertisementPost> findByAdvertiserEmailAndPostDate(String advertiserEmail, LocalDate postDate);

    
    void deleteByAdvertiserEmailAndPostDate(String advertiserEmail, LocalDate postDate);

    void deleteAllByAdvertiserEmail(String advertiserEmail);
    Optional<AdvertisementPost> findByAdvertiserAndPostDate(Advertiser advertiser, LocalDate postDate);
    Optional<AdvertisementPost> findById(Long id);

	void save(AdvertiserComment comment);

	

	

	
}
