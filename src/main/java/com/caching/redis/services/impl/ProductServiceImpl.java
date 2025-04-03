package com.caching.redis.services.impl;

import com.caching.redis.dao.ProductDao;
import com.caching.redis.models.Product;
import com.caching.redis.services.framework.ProductService;
import com.caching.redis.services.pub_sub.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final EventPublisher eventPublisher;
    @Cacheable(value = "products", key = "#id") //this used to get the object from the cached memory.
    @Override
    public Optional<Product> getProductById(Long id) {
        log.warn("fetching product from database.....");
        eventPublisher.publishEvent("Get Sensitive Data", "Product Id: " + id);
        return productDao.findById(id);
    }

    @CachePut(value = "products", key = "#product.id") //this annotation used add the saved object to the cache.
    @Override
    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    @CacheEvict(value = "products", key = "#id") //this to delete this from the object from the cache
    @Override
    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
}
