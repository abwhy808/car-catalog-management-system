package com.example.carcatalog.repository;
import com.example.carcatalog.entity.Car;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface CarRepository extends JpaRepository<Car,Long> {
 boolean existsByModelId(Long modelId);
 @Query("select c from Car c where (:manufacturerId is null or c.model.manufacturer.id=:manufacturerId) and (:modelId is null or c.model.id=:modelId) and (:q is null or lower(c.model.name) like lower(concat('%',cast(:q as string),'%')) or lower(c.model.manufacturer.name) like lower(concat('%',cast(:q as string),'%')) or lower(c.color) like lower(concat('%',cast(:q as string),'%'))) order by c.id desc")
 List<Car> search(@Param("q") String q,@Param("manufacturerId") Long manufacturerId,@Param("modelId") Long modelId);
}
