package com.example.carcatalog.controller;
import com.example.carcatalog.service.VehicleModelService;
import com.example.carcatalog.dto.ModelRequest;
import com.example.carcatalog.entity.VehicleModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/models") public class VehicleModelController {
 private final VehicleModelService service;public VehicleModelController(VehicleModelService service){this.service=service;}
 @GetMapping public List<VehicleModel> list(@RequestParam(required=false) Long manufacturerId){return service.list(manufacturerId);}
 @GetMapping("/{id}") public VehicleModel get(@PathVariable Long id){return service.get(id);}
 @PostMapping public VehicleModel add(@Valid @RequestBody ModelRequest request){return service.save(null,request);}
 @PutMapping("/{id}") public VehicleModel update(@PathVariable Long id,@Valid @RequestBody ModelRequest request){return service.save(id,request);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
