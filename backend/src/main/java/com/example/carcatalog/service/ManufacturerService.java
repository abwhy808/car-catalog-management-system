package com.example.carcatalog.service;
import com.example.carcatalog.dto.ManufacturerRequest;
import com.example.carcatalog.entity.Manufacturer;
import com.example.carcatalog.repository.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class ManufacturerService {
 private final ManufacturerRepository repo; private final VehicleModelRepository models;
 public ManufacturerService(ManufacturerRepository repo,VehicleModelRepository models){this.repo=repo;this.models=models;}
 public List<Manufacturer> list(){return repo.findAll();}
 public Manufacturer get(Long id){return repo.findById(id).orElseThrow(()->new NoSuchElementException("Manufacturer not found"));}
 public Manufacturer save(Long id,ManufacturerRequest req){Manufacturer m=id==null?new Manufacturer():get(id);m.setName(req.name().trim());m.setCountry(req.country());m.setLogoUrl(req.logoUrl());return repo.save(m);}
 public void delete(Long id){get(id);if(models.existsByManufacturerId(id))throw new IllegalArgumentException("Delete this manufacturer's models first");repo.deleteById(id);}
}
