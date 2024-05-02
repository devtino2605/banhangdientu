package com.example.web_banhang.Services;

import com.example.web_banhang.model.Manufacturer;

import java.util.List;
    public interface ManufacturerService {
        public Manufacturer add(Manufacturer manufacturer);
        public Manufacturer update(int id,Manufacturer manufacturer);
        public void delete(int id);
        public Manufacturer getManufacturer(int id);
        public List<Manufacturer> getAll();
    }
