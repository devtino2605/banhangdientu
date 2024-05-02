package com.example.web_banhang.Services.Imp;

import com.example.web_banhang.Services.ManufacturerService;
import com.example.web_banhang.exception.ResourceNotFoundException;
import com.example.web_banhang.model.Manufacturer;
import com.example.web_banhang.reponsibility.ManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImp implements ManufacturerService {
    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Override
    public Manufacturer add(Manufacturer manufacturer) {
        return manufacturerRepo.save(manufacturer);
    }

    @Override
    public Manufacturer update(int id, Manufacturer manufacturer) {
        Manufacturer updatefacturer1=manufacturerRepo.findById(id).get();

        updatefacturer1.setProducts(manufacturer.getProducts());
        updatefacturer1.setName(manufacturer.getName());
        updatefacturer1.setThumbnal(manufacturer.getThumbnal());
        updatefacturer1.setDescription(manufacturer.getDescription());

        return manufacturerRepo.save(updatefacturer1);
    }

    @Override
    public void delete(int id) {
        manufacturerRepo.deleteById(id);
    }

    @Override
    public Manufacturer getManufacturer(int id) {
        return manufacturerRepo.getReferenceById(id);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerRepo.findAll();
    }
}
