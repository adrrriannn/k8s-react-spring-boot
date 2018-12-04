package com.adrrriannn.store.product.controller;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.product.service.ProductService;
import com.adrrriannn.store.product.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private ProductService productService;
    private ProductValidator productValidator;

    @Autowired
    public ProductController(ProductService productService,
                             ProductValidator productValidator) {
        this.productService = productService;
        this.productValidator = productValidator;
    }

    @InitBinder("product")
    void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(productValidator);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody @Valid ProductDto product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") String productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/search")
    public List<ProductDto> searchProductsByKeywords(@RequestParam("keywords") String keywords) {
        return productService.searchProductsByKeywords(keywords);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
}
