package com.example.springredis.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  @Cacheable(cacheNames = "productList")
  public List<ProductEntity> getAll() {
    log.info("Get all products");

    return productRepository.findAll();
  }

  @Cacheable(cacheNames = "product", key = "#id.toString()")
  public ProductEntity get(Long id) {
    log.info("Get product with id: {}", id);

    return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product was not found."));
  }

  @CacheEvict(cacheNames = "productList", allEntries = true)
  public ProductEntity create(ProductCreationRequest request) {
    ProductEntity productEntity = ProductEntity.builder()
        .name(request.name())
        .price(request.price())
        .quantity(request.quantity())
        .build();

    return productRepository.save(productEntity);
  }

  @Caching(evict = {
      @CacheEvict(cacheNames = "productList", allEntries = true),
      @CacheEvict(cacheNames = "product", key = "#id.toString()")
  })
  public ProductEntity partialUpdate(Long id, BigDecimal price, int quantity) {
    ProductEntity productEntity = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product was not found."));

    productEntity.setPrice(price);
    productEntity.setQuantity(quantity);

    return productRepository.save(productEntity);
  }

}
