package com.adrrriannn.store.product.repository;

import com.adrrriannn.store.product.entity.Product;

import java.util.List;

public interface IndexingRepository {

    List<Product> searchProductsByKeywords(String keywords);
    void index(Product product);
}
