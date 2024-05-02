package com.example.web_banhang.controller;

import com.example.web_banhang.Services.Imp.ManufacturerServiceImp;
import com.example.web_banhang.model.Manufacturer;
import com.example.web_banhang.model.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class ManufacturerController {
    @Autowired
    private ManufacturerServiceImp manufacturerServiceImp;

    @GetMapping("/manufacturer")
    public ResponseEntity<List<Manufacturer>> getAll(){
        List<Manufacturer> categories = manufacturerServiceImp.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/manufacturer/{id}")
    public ResponseEntity<Manufacturer> getManufacturer(@PathVariable("id") int id){
        return ResponseEntity.ok(manufacturerServiceImp.getManufacturer(id));
    }

    @PostMapping("/manufacturer/add")
    public ResponseEntity<Manufacturer> add(@RequestBody Manufacturer manufacturer){
        Manufacturer saveCate = manufacturerServiceImp.add(manufacturer);
        return new ResponseEntity<>(saveCate, HttpStatus.CREATED);
    }

    @PutMapping("/manufacturer/update/{id}")
    public ResponseEntity<Manufacturer> updateEmp(@PathVariable("id") int id, @RequestBody Manufacturer manufacturer){
        Manufacturer updateManufacturer = manufacturerServiceImp.update(id , manufacturer);
        return ResponseEntity.ok(updateManufacturer);
    }


    @DeleteMapping("/manufacturer/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        manufacturerServiceImp.delete(id);
        return ResponseEntity.ok("delete manufacturer success");
    }
}
