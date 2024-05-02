package com.example.web_banhang.reponsibility;

import com.example.web_banhang.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepo extends JpaRepository<Manufacturer,Integer> {
}
