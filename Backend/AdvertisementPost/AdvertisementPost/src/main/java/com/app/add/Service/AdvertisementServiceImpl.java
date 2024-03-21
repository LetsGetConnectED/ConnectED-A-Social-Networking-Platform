package com.app.add.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.add.Model.Advertisement;
import com.app.add.Repository.AdvertisementRepository;

import java.util.Date;
import java.util.List;

//@Service
//public class AdvertisementServiceImpl implements AdvertisementService {
//
//    @Autowired
//    private AdvertisementRepository advertisementRepository;
//
//    @Override
//    public List<Advertisement> getAllAdvertisements() {
//        return advertisementRepository.findAll();
//    }
//
//    @Override
//    public Advertisement getAdvertisementById(Long id) {
//        return advertisementRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public Advertisement createAdvertisement(Advertisement advertisement) {
//        advertisement.setDateCreated(new Date());
//        return advertisementRepository.save(advertisement);
//    }
//
//    @Override
//    public Advertisement updateAdvertisement(Long id, Advertisement advertisement) {
//        Advertisement existingAdvertisement = advertisementRepository.findById(id).orElse(null);
//        if (existingAdvertisement != null) {
//            existingAdvertisement.setTitle(advertisement.getTitle());
//            existingAdvertisement.setDescription(advertisement.getDescription());
//            existingAdvertisement.setContent(advertisement.getContent());
//            existingAdvertisement.setLikes(advertisement.getLikes());
//            existingAdvertisement.setImageUrl(advertisement.getImageUrl());
//            return advertisementRepository.save(existingAdvertisement);
//        }
//        return null;
//    }
//
//    @Override
//    public void deleteAdvertisement(Long id) {
//        advertisementRepository.deleteById(id);
//    }
//}
//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> getAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    @Override
    public Advertisement getAdvertisementById(Long id) {
        return advertisementRepository.findById(id).orElse(null);
    }

    @Override
    public Advertisement createAdvertisement(Advertisement advertisement) {
        advertisement.setDateCreated(new Date());
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement updateAdvertisement(Advertisement advertisementDetails) {
        Advertisement advertisement = advertisementRepository.findById(advertisementDetails.getId()).orElse(null);
        if (advertisement != null) {
            if (advertisementDetails.getTitle() != null) {
                advertisement.setTitle(advertisementDetails.getTitle());
            }
            if (advertisementDetails.getDescription() != null) {
                advertisement.setDescription(advertisementDetails.getDescription());
            }
            if (advertisementDetails.getContent() != null) {
                advertisement.setContent(advertisementDetails.getContent());
            }
            if (advertisementDetails.getLikes() >= 0) {
                advertisement.setLikes(advertisementDetails.getLikes());
            }
            if (advertisementDetails.getImageUrl() != null) {
                advertisement.setImageUrl(advertisementDetails.getImageUrl());
            }
            return advertisementRepository.save(advertisement);
        }
        return null;
    }

    @Override
    public void deleteAdvertisement(Long id) {
        advertisementRepository.deleteById(id);
    }
}

