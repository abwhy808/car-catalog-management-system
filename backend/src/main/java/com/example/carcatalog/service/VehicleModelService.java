package com.example.carcatalog.service;
import com.example.carcatalog.dto.ModelRequest;
import com.example.carcatalog.entity.VehicleModel;
import com.example.carcatalog.repository.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class VehicleModelService {
 private final VehicleModelRepository repo;private final ManufacturerService manufacturers;private final CarRepository cars;
 public VehicleModelService(VehicleModelRepository repo,ManufacturerService manufacturers,CarRepository cars){this.repo=repo;this.manufacturers=manufacturers;this.cars=cars;}
 public List<VehicleModel> list(Long manufacturerId){return manufacturerId==null?repo.findAll():repo.findByManufacturerId(manufacturerId);}
 public VehicleModel get(Long id){return repo.findById(id).orElseThrow(()->new NoSuchElementException("Model not found"));}
 public VehicleModel save(Long id,ModelRequest req){VehicleModel m=id==null?new VehicleModel():get(id);m.setName(req.name().trim());m.setBodyType(req.bodyType());m.setManufacturer(manufacturers.get(req.manufacturerId()));return repo.save(m);}
 public void delete(Long id){get(id);if(cars.existsByModelId(id))throw new IllegalArgumentException("Delete cars for this model first");repo.deleteById(id);}
}
