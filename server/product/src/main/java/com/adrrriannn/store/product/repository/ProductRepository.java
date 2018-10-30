package com.adrrriannn.store.product.repository;

import com.adrrriannn.store.product.entity.Product;

import java.util.List;

public interface ProductRepository {

    Product findById(String id);
    List<Product> findByKeywords(String keywords);
    Product save(Product product);
}
