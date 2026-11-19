package com.example.carcatalog.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
@RestController @RequestMapping("/api/uploads") public class UploadController {
 @Value("${app.upload-dir:uploads}") private String uploadDir;
 @PostMapping(consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
 public Map<String,String> upload(@RequestParam("file") MultipartFile file) throws IOException {
  if(file.isEmpty())throw new IllegalArgumentException("Choose an image");
  String type=file.getContentType();if(type==null||!Set.of("image/jpeg","image/png","image/webp","image/gif").contains(type))throw new IllegalArgumentException("Only JPG, PNG, WEBP and GIF images are allowed");
  String ext=switch(type){case "image/jpeg"->".jpg";case "image/png"->".png";case "image/webp"->".webp";default->".gif";};
  Path dir=Paths.get(uploadDir).toAbsolutePath().normalize();Files.createDirectories(dir);
  String name=UUID.randomUUID()+ext;Files.copy(file.getInputStream(),dir.resolve(name));
  return Map.of("url","/uploads/"+name);
 }
}
