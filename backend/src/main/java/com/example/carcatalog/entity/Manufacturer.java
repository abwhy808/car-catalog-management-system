package com.example.carcatalog.entity;
import jakarta.persistence.*;
@Entity @Table(name="manufacturers")
public class Manufacturer {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=100) private String name;
 private String country;
 private String logoUrl;
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getName(){return name;} public void setName(String v){name=v;}
 public String getCountry(){return country;} public void setCountry(String v){country=v;}
 public String getLogoUrl(){return logoUrl;} public void setLogoUrl(String v){logoUrl=v;}
}
