package com.internship.HospitalManagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5500") // Add the origin of your frontend application
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Add the allowed HTTP methods
                        .allowedHeaders("*"); // Add the allowed headers
            }
        };
    }
}
