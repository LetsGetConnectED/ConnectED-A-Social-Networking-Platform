package com.app.add.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.add.Model.Advertisement;

//@Service
//public interface AdvertisementService {
//    List<Advertisement> getAllAdvertisements();
//    Advertisement getAdvertisementById(Long id);
//    Advertisement createAdvertisement(Advertisement advertisement);
//    Advertisement updateAdvertisement(Long id, Advertisement advertisement);
//    void deleteAdvertisement(Long id);
//}
import java.util.List;

@Service
public interface AdvertisementService {
    List<Advertisement> getAllAdvertisements();
    Advertisement getAdvertisementById(Long id);
    Advertisement createAdvertisement(Advertisement advertisement);
    Advertisement updateAdvertisement(Advertisement advertisement);
    void deleteAdvertisement(Long id);
}

