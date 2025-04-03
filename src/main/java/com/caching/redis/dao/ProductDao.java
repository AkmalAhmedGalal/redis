package com.caching.redis.dao;

import com.caching.redis.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDao extends JpaRepository<Product,Long> {
}
