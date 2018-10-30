package com.adrrriannn.store.product.mapper;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto map(Product product) {
        if(product == null) {
            return ProductDto.builder().build();
        }

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

    public Product map(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .build();
    }
}
