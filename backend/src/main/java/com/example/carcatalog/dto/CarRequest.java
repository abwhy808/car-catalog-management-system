package com.example.carcatalog.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CarRequest(@NotNull Long modelId,@NotNull @Min(1886) @Max(2100) Integer year,
 @NotNull @DecimalMin("0.0") BigDecimal price,String color,@Min(0) Integer mileage,
 String fuelType,String transmission,String imageUrl,String description) {}
