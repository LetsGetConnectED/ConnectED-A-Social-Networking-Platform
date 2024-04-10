package com.connected.advertisement.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connected.advertisement.model.AdvertisementPost;
import com.connected.advertisement.model.Advertiser;
import com.connected.advertisement.model.Comment;

@Repository
public interface AdvertisementPostRepository extends JpaRepository<AdvertisementPost, Long> {

    List<AdvertisementPost> findAllByAdvertiserEmail(String advertiserEmail);

    Optional<AdvertisementPost> findByAdvertiserEmailAndPostDate(String advertiserEmail, LocalDate postDate);

    void deleteByAdvertiserEmailAndPostDate(String advertiserEmail, LocalDate postDate);

    void deleteAllByAdvertiserEmail(String advertiserEmail);

    Optional<AdvertisementPost> findByAdvertiserAndPostDate(Advertiser advertiser, LocalDate postDate);

    Optional<AdvertisementPost> findById(Long id);

    void save(Comment comment);
}
