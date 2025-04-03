package com.caching.redis.configurations;

import com.caching.redis.interceptors.RateLimitInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {

    private final RateLimitInterceptor rateLimitInterceptor;

    private final static String[] UNSECURED_APIs = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v2/**",
            "/v3/**",
            "/api-docs/**"
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor)
                .addPathPatterns("/api/**");
    }

}
