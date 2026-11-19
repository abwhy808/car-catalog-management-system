package com.example.carcatalog.controller;
import com.example.carcatalog.service.CarService;
import com.example.carcatalog.dto.CarRequest;
import com.example.carcatalog.entity.Car;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/cars") public class CarController {
 private final CarService service;public CarController(CarService service){this.service=service;}
 @GetMapping public List<Car> list(@RequestParam(required=false) String q,@RequestParam(required=false) Long manufacturerId,@RequestParam(required=false) Long modelId){return service.list(q,manufacturerId,modelId);}
 @GetMapping("/{id}") public Car get(@PathVariable Long id){return service.get(id);}
 @PostMapping public Car add(@Valid @RequestBody CarRequest request){return service.save(null,request);}
 @PutMapping("/{id}") public Car update(@PathVariable Long id,@Valid @RequestBody CarRequest request){return service.save(id,request);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
