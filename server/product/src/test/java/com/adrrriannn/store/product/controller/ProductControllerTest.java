package com.adrrriannn.store.product.controller;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.product.validator.ProductValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import com.adrrriannn.store.product.service.ProductService;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductValidator productValidator;

    private ProductController productController;

    private ProductDto product = ProductDto.builder()
            .id("id")
            .name("Product name")
            .description("This is a product description")
            .stock(20)
            .build();

    @Before
    public void setUp() {
        productController = new ProductController(productService, productValidator);
    }

    @Test
    public void saveProductTest() {
        doReturn(product).when(productService).save(any());

        ProductDto response = productController.createProduct(product);

        assertEquals(response, product);
    }

    @Test
    public void getProductTest() {
        doReturn(product).when(productService).getProductById(any());

        ProductDto response = productController.getProduct("productId");

        assertEquals(response, product);
    }

    @Test
    public void searchProductByKeywordsTest() {
        List<ProductDto> productDtos = Arrays.asList(product);

        doReturn(productDtos).when(productService).searchProductsByKeywords(anyString());

        List<ProductDto> response = productController.searchProductsByKeywords("product 1");

        assertEquals(response, productDtos);
    }
}
