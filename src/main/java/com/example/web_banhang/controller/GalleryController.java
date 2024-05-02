package com.example.web_banhang.controller;

import com.example.web_banhang.Services.Imp.GalleryServiceImp;
import com.example.web_banhang.model.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class GalleryController {
    @Autowired
    private GalleryServiceImp galleryServiceImp;

    @GetMapping("/gallery")
    public ResponseEntity<List<Gallery>> getAll(){
        List<Gallery> categories = galleryServiceImp.getAllGallery();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/gallery/{id}")
    public ResponseEntity<Gallery> getGallery(@PathVariable("id") int id){
        return ResponseEntity.ok(galleryServiceImp.getGalleryById(id));
    }

    @GetMapping("/gallery_statys/{status}")
    public ResponseEntity<List<Gallery>> getGalleryByStatus(@PathVariable("status") int status){
        List<Gallery> galleries = galleryServiceImp.getByStatus(status);

        if (galleries.isEmpty()) {
            // Trạng thái không tồn tại, trả về thông báo lỗi
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(galleries);
    }


    @PostMapping("/gallery/add")
    public ResponseEntity<Gallery> add(@RequestBody Gallery Gallery){
        Gallery saveCate = galleryServiceImp.createGallery(Gallery);
        return new ResponseEntity<>(saveCate, HttpStatus.CREATED);
    }

    @PutMapping("/gallery/update/{id}")
    public ResponseEntity<Gallery> updateEmp(@PathVariable("id") int id, @RequestBody Gallery Gallery){
        Gallery updateGallery = galleryServiceImp.updatwGallery(id , Gallery);
        return ResponseEntity.ok(updateGallery);
    }


    @DeleteMapping("/gallery/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        galleryServiceImp.deleteGallery(id);
        return ResponseEntity.ok("delete Gallery success");
    }
}
