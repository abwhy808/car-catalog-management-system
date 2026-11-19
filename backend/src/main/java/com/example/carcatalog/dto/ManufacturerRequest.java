package com.example.carcatalog.dto;
import jakarta.validation.constraints.NotBlank;
public record ManufacturerRequest(@NotBlank String name,String country,String logoUrl) {}
