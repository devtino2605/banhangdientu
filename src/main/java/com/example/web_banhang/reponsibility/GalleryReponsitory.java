package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryReponsitory extends JpaRepository<Gallery,Integer> {
    List<Gallery> findAllByStatus(int status);
}
