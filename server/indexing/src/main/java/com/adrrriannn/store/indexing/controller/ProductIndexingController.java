package com.adrrriannn.store.indexing.controller;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.indexing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductIndexingController {

    private ProductService productService;

    @Autowired
    public ProductIndexingController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public List<ProductDto> searchProductsByKeywords(@RequestParam("keywords") String keywords) {
        return productService.searchProductsByKeywords(keywords);
    }
}
