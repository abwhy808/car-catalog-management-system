package com.example.carcatalog.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import java.nio.file.*;
@Configuration public class WebConfig implements WebMvcConfigurer {
 @Value("${app.upload-dir:uploads}") private String uploadDir;
 @Override public void addCorsMappings(CorsRegistry registry){registry.addMapping("/api/**").allowedOrigins("http://localhost:5173","http://127.0.0.1:5173").allowedMethods("GET","POST","PUT","DELETE","OPTIONS");}
 @Override public void addResourceHandlers(ResourceHandlerRegistry registry){String location=Paths.get(uploadDir).toAbsolutePath().normalize().toUri().toString();if(!location.endsWith("/"))location+="/";registry.addResourceHandler("/uploads/**").addResourceLocations(location);}
}
