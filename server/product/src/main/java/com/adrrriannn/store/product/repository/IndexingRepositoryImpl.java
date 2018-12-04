package com.adrrriannn.store.product.repository;

import com.adrrriannn.store.dto.ProductDto;
import com.adrrriannn.store.product.entity.Product;
import com.adrrriannn.store.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndexingRepositoryImpl implements IndexingRepository {

    private ProductMapper productMapper;
    private MessageChannel messageChannel;
    private String productIndexingTopic;
    private RestTemplate restTemplate;
    private String indexingServiceHost;
    private String indexingProductPath;

    @Autowired
    public IndexingRepositoryImpl(ProductMapper productMapper,
                                  @Lazy @Qualifier("producingChannel") MessageChannel messageChannel,
                                  @Value("${kafka.topic.product-indexing}") String productIndexingTopic,
                                  @Value("${indexing.service.host}") String indexingServiceHost,
                                  @Value("${indexing.service.product.path}") String indexingProductPath) {
        this.productMapper = productMapper;
        this.messageChannel = messageChannel;
        this.productIndexingTopic = productIndexingTopic;
        this.restTemplate = new RestTemplate();
        this.indexingServiceHost = indexingServiceHost;
        this.indexingProductPath = indexingProductPath;
    }

    @Override
    public List<Product> searchProductsByKeywords(String keywords) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<List<ProductDto>> products = restTemplate.exchange(
                indexingServiceHost + indexingProductPath + "?keywords=" + keywords,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<ProductDto>>() {
                }
        );

        return products.getBody().stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void index(Product product) {

        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, productIndexingTopic);
        MessageHeaders messageHeaders = new MessageHeaders(headers);

        ProductDto productDto = productMapper.map(product);

        Message<ProductDto> message = MessageBuilder.createMessage(productDto, messageHeaders);
        messageChannel.send(message);
    }

}
