package com.clone.amazon.configration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Use ** to cover all API endpoints
                .allowedOrigins("http://localhost:5173")  // Correct the origin
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Methods you want to allow
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials like cookies
    }
}
