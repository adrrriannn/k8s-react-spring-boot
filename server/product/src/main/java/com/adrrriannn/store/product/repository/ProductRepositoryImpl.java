package com.adrrriannn.store.product.repository;

import com.adrrriannn.store.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private ProductJpaRepository productJpaRepository;
    private IndexingRepository indexingRepository;

    @Autowired
    public ProductRepositoryImpl(ProductJpaRepository productJpaRepository,
                                 IndexingRepository indexingRepository) {
        this.productJpaRepository = productJpaRepository;
        this.indexingRepository = indexingRepository;
    }

    @Override
    public Product findById(String id) {
        return productJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByKeywords(String keywords) {
        return indexingRepository.searchProductsByKeywords(keywords);
    }

    @Override
    public Product save(Product product) {
        product = productJpaRepository.save(product);
        indexingRepository.index(product);

        return product;
    }
}
