package com.example.web_banhang.Services.Imp;


import com.example.web_banhang.Services.GalleryService;
import com.example.web_banhang.exception.ResourceNotFoundException;
import com.example.web_banhang.model.Gallery;
import com.example.web_banhang.reponsibility.GalleryReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryServiceImp implements GalleryService {
    @Autowired
    private GalleryReponsitory galleryReponsitory;
    @Override
    public Gallery createGallery(Gallery Gallery) {
        return galleryReponsitory.save(Gallery);
    }

    @Override
    public Gallery getGalleryById(int id) {
        return galleryReponsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("not exist"));
    }

    @Override
    public List<Gallery> getAllGallery() {
        return galleryReponsitory.findAll();
    }

    @Override
    public Gallery updatwGallery(int id, Gallery updateGallery) {
        Gallery gallery = galleryReponsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("not exist"));
        gallery.setThumbnail(updateGallery.getThumbnail());
        gallery.setStatus(updateGallery.getStatus());
        return galleryReponsitory.save(gallery);
    }

    @Override
    public void deleteGallery(int id) {
        Gallery gallery = galleryReponsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("not exist"));
        galleryReponsitory.delete(gallery);
    }


    @Override
    public List<Gallery> getByStatus(int status) {
        return galleryReponsitory.findAllByStatus(status);
    }
}
