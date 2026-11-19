package com.example.carcatalog.entity;
import jakarta.persistence.*;
@Entity @Table(name="vehicle_models",uniqueConstraints=@UniqueConstraint(columnNames={"manufacturer_id","name"}))
public class VehicleModel {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String name;
 private String bodyType;
 @ManyToOne(optional=false,fetch=FetchType.EAGER) @JoinColumn(name="manufacturer_id",nullable=false) private Manufacturer manufacturer;
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getBodyType(){return bodyType;} public void setBodyType(String v){bodyType=v;}
 public String getName(){return name;} public void setName(String v){name=v;}
 public Manufacturer getManufacturer(){return manufacturer;} public void setManufacturer(Manufacturer v){manufacturer=v;}
}
