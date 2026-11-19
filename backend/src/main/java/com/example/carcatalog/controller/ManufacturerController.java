package com.example.carcatalog.controller;
import com.example.carcatalog.service.ManufacturerService;
import com.example.carcatalog.dto.ManufacturerRequest;
import com.example.carcatalog.entity.Manufacturer;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/manufacturers") public class ManufacturerController {
 private final ManufacturerService service;public ManufacturerController(ManufacturerService service){this.service=service;}
 @GetMapping public List<Manufacturer> list(){return service.list();}
 @GetMapping("/{id}") public Manufacturer get(@PathVariable Long id){return service.get(id);}
 @PostMapping public Manufacturer add(@Valid @RequestBody ManufacturerRequest request){return service.save(null,request);}
 @PutMapping("/{id}") public Manufacturer update(@PathVariable Long id,@Valid @RequestBody ManufacturerRequest request){return service.save(id,request);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
