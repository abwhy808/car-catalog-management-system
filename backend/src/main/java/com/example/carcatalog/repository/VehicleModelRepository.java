package com.example.carcatalog.repository;
import com.example.carcatalog.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface VehicleModelRepository extends JpaRepository<VehicleModel,Long> {
 List<VehicleModel> findByManufacturerId(Long manufacturerId);
 boolean existsByManufacturerId(Long manufacturerId);
}
