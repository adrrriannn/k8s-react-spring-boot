package com.adrrriannn.store.product.service;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.product.entity.Product;
import com.adrrriannn.store.product.exception.ProductNotFoundException;
import com.adrrriannn.store.product.mapper.ProductMapper;
import com.adrrriannn.store.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private ProductService productService;

    private ProductDto productDto = ProductDto.builder()
            .name("Product")
            .description("Description")
            .build();

    private Product product = Product.builder()
            .id("id")
            .name("Product")
            .description("Description")
            .build();

    @Before
    public void setUp() {
        productService = new ProductServiceImpl(productRepository, productMapper);
    }

    @Test
    public void saveProductTest() {
        doReturn(productDto).when(productMapper).map(product);
        doReturn(product).when(productMapper).map(productDto);

        doReturn(product).when(productRepository).save(product);

        ProductDto result = productService.save(productDto);
        assertEquals(productDto, result);
    }

    @Test
    public void getProductByIdTest() {
        doReturn(productDto).when(productMapper).map(product);
        doReturn(product).when(productRepository).findById(anyString());

        ProductDto result = productService.getProductById("productId");
        assertEquals(productDto, result);
    }

    @Test(expected = ProductNotFoundException.class)
    public void getProductByIdNotFoundExceptionTest() {
        doReturn(null).when(productRepository).findById(anyString());

        productService.getProductById("productId");
    }

    @Test
    public void searchProductsByKeywords() {
        List<ProductDto> productDtos = Arrays.asList(productDto);
        List<Product> productList = Arrays.asList(product);

        List<String> productIds = productDtos.stream()
                .map(ProductDto::getId)
                .collect(Collectors.toList());

        doReturn(productList).when(productRepository).findByKeywords(anyString());
        doReturn(productDto).when(productMapper).map(any(Product.class));

        List<ProductDto> result = productService.searchProductsByKeywords("some keywords");

        assertEquals(result, productDtos);
    }

}
