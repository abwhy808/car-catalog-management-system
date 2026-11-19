package com.example.carcatalog.entity;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="cars")
public class Car {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="model_id",nullable=false) private VehicleModel model;
 @Column(nullable=false) private Integer year;
 @Column(nullable=false,precision=14,scale=2) private BigDecimal price;
 private String color;
 private Integer mileage;
 private String fuelType;
 private String transmission;
 private String imageUrl;
 @Column(length=2000) private String description;
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public VehicleModel getModel(){return model;} public void setModel(VehicleModel v){model=v;}
 public Integer getYear(){return year;} public void setYear(Integer v){year=v;}
 public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal v){price=v;}
 public String getColor(){return color;} public void setColor(String v){color=v;}
 public Integer getMileage(){return mileage;} public void setMileage(Integer v){mileage=v;}
 public String getFuelType(){return fuelType;} public void setFuelType(String v){fuelType=v;}
 public String getTransmission(){return transmission;} public void setTransmission(String v){transmission=v;}
 public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
}
