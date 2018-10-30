package com.adrrriannn.store.product.repository;

import com.adrrriannn.store.product.entity.Product;
import com.adrrriannn.store.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexingRepositoryImpl implements IndexingRepository {

    private ProductMapper productMapper;

    @Autowired
    public IndexingRepositoryImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> searchProductsByKeywords(String keywords) {
        return new ArrayList<>();
    }

    @Override
    public void index(Product product) {

    }
}
