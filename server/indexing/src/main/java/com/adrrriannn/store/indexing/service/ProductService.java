package com.adrrriannn.store.indexing.service;

import com.adrrriannn.store.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> searchProductsByKeywords(String keywords);
}
