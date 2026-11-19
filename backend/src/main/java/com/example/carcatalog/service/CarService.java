package com.example.carcatalog.service;
import com.example.carcatalog.dto.CarRequest;
import com.example.carcatalog.entity.Car;
import com.example.carcatalog.repository.CarRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class CarService {
 private final CarRepository repo;private final VehicleModelService models;
 public CarService(CarRepository repo,VehicleModelService models){this.repo=repo;this.models=models;}
 public List<Car> list(String q,Long manufacturerId,Long modelId){return repo.search(q==null||q.isBlank()?null:q.trim(),manufacturerId,modelId);}
 public Car get(Long id){return repo.findById(id).orElseThrow(()->new NoSuchElementException("Car not found"));}
 public Car save(Long id,CarRequest req){Car c=id==null?new Car():get(id);c.setModel(models.get(req.modelId()));c.setYear(req.year());c.setPrice(req.price());c.setColor(req.color());c.setMileage(req.mileage());c.setFuelType(req.fuelType());c.setTransmission(req.transmission());c.setImageUrl(req.imageUrl());c.setDescription(req.description());return repo.save(c);}
 public void delete(Long id){get(id);repo.deleteById(id);}
}
