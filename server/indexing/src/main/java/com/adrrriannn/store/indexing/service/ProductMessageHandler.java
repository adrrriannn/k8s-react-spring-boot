package com.adrrriannn.store.indexing.service;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.indexing.repository.elasticsearch.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.io.IOException;

public class ProductMessageHandler implements MessageHandler {

    private ProductRepository productRepository;

    @Autowired
    public ProductMessageHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {

        Object object = message.getPayload();
        try {
            productRepository.save((ProductDto) object);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
