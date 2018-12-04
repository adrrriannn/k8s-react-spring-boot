package com.adrrriannn.store.product.service;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.product.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto);
    ProductDto getProductById(String id);
    List<ProductDto> searchProductsByKeywords(String keywords);
    List<ProductDto> getAllProducts();
}
