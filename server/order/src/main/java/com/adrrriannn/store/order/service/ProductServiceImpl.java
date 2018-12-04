package com.adrrriannn.store.order.service;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.order.exception.InternalServerErrorException;
import com.adrrriannn.store.order.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private RestTemplate restTemplate;
    private String productServiceHost;
    private String getProductPath;

    @Autowired
    public ProductServiceImpl(@Value("${product.service.host}") String productServiceHost,
                              @Value("${product.service.get-product.path}") String getProductPath) {
        this.restTemplate = new RestTemplate();
        this.productServiceHost = productServiceHost;
        this.getProductPath = getProductPath;
    }


    @Override
    public ProductDto getProductById(String id) throws ProductNotFoundException {

        String url = productServiceHost + getProductPath + id;

        try {
            ResponseEntity<ProductDto> response = restTemplate.getForEntity(url, ProductDto.class);
            return response.getBody();
        } catch(HttpStatusCodeException ex) {
            log.debug("Client error when getting product with id : {}", id);
            if(HttpStatus.NOT_FOUND.equals(ex.getStatusCode())) {
                throw new ProductNotFoundException(id);
            } else {
                throw ex;
            }
        } catch(RestClientException ex) {
            log.error("Internal server error when getting product with id: {}", id);
            throw new InternalServerErrorException("Internal server error when Internal server error when getting product with id " + id + " - Exception : " + ex);
        }
    }

}
