package com.adrrriannn.store.indexing.service;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.indexing.repository.elasticsearch.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> searchProductsByKeywords(String keywords) {
        return productRepository.getProductsByKeywords(keywords);
    }
}
