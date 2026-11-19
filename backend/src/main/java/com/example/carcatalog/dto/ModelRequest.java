package com.example.carcatalog.dto;
import jakarta.validation.constraints.*;
public record ModelRequest(@NotBlank String name,@NotNull Long manufacturerId,String bodyType) {}
