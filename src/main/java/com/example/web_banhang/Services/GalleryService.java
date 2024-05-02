package com.example.web_banhang.Services;

import com.example.web_banhang.model.Gallery;

import java.util.List;

public interface GalleryService {
    Gallery createGallery(Gallery Gallery);
    Gallery getGalleryById(int id);
    List<Gallery> getAllGallery();

    Gallery updatwGallery(int id, Gallery updateGallery);

    void deleteGallery(int id);

    List<Gallery> getByStatus(int id);
}
