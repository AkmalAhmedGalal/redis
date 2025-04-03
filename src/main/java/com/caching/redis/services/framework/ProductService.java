package com.caching.redis.services.framework;

import com.caching.redis.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Optional<Product> getProductById(Long id);

    public Product saveProduct(Product product);

    public void deleteProduct(Long id);

    public List<Product> getAllProducts();

}
