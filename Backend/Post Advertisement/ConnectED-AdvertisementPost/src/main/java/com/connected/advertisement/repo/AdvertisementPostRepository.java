package com.connected.advertisement.repo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.advertisement.model.AdvertisementPost;

@Repository
public interface AdvertisementPostRepository extends JpaRepository<AdvertisementPost, Long> {
    // Find all posts by advertiser's email
    List<AdvertisementPost> findAllByEmail(String email);
    // Find post by advertiser's email and post date
    Optional<AdvertisementPost> findByEmailAndPostDate(String email, LocalDateTime postDate);
    // Delete post by advertiser's email and post date
    void deleteByEmailAndPostDate(String email, LocalDateTime postDate);
    // Delete all posts by advertiser's email
    void deleteAllByEmail(String email);
}
