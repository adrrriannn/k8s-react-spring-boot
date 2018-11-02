package com.adrrriannn.store.indexing.service;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.indexing.repository.elasticsearch.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductMessageHandler {

    private ProductRepository productRepository;

    @Autowired
    public ProductMessageHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics="${kafka.topic.index-product}")
    public void handleMessage(ProductDto productDto) throws MessagingException {

        try {
            productRepository.save(productDto);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
