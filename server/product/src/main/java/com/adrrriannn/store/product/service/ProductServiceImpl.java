package com.adrrriannn.store.product.service;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.product.entity.Product;
import com.adrrriannn.store.product.exception.ProductNotFoundException;
import com.adrrriannn.store.product.mapper.ProductMapper;
import com.adrrriannn.store.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.map(productDto);
        product = productRepository.save(product);
        return productMapper.map(product);
    }

    @Override
    public ProductDto getProductById(String id) throws ProductNotFoundException {
        Product product = productRepository.findById(id);
        if(product == null) {
            throw new ProductNotFoundException();
        }

        return productMapper.map(product);
    }

    @Override
    public List<ProductDto> searchProductsByKeywords(String keywords) {
        return productRepository.findByKeywords(keywords).stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
    }
}
