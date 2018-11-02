package com.adrrriannn.store.indexing.repository.elasticsearch;

import com.adrrriannn.store.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ProductRepository {

    private ObjectMapper objectMapper;
    private RestHighLevelClient esClient;

    @Autowired
    public ProductRepository(ObjectMapper objectMapper,
                             RestHighLevelClient esClient) {
        this.objectMapper = objectMapper;
        this.esClient = esClient;
    }

    public void save(ProductDto productDto) throws IOException {
        byte[] bytes = objectMapper.writeValueAsBytes(productDto);
        esClient.index(new IndexRequest("product", "_doc", productDto.getId()).source(bytes, XContentType.JSON), RequestOptions.DEFAULT);
    }

    public List<ProductDto> getProductsByKeywords(String keywords) {

        log.debug("Searching product by keywords: {}", keywords);

        SearchResponse searchResponse;
        try {
            searchResponse = esClient.search(new SearchRequest("product")
                    .source(new SearchSourceBuilder()
                        .query(
                                new SimpleQueryStringBuilder(keywords)
                        )
                    ), RequestOptions.DEFAULT);
        } catch (IOException ex) {
            throw new RuntimeException("Error connection to Elasticsearch", ex);
        }


        List<ProductDto> productDtoList = new ArrayList<>();
        for(SearchHit searchHit : searchResponse.getHits()) {
            try {
                ProductDto productDto = objectMapper.readValue(searchHit.getSourceAsString(), ProductDto.class);
                productDtoList.add(productDto);
            } catch (IOException ex) {
                log.error("Error deserialising object from elasticsearch hit - Object as String : {} - Exception : {}", searchHit.getSourceAsString(), ex);
            }
        }

        return productDtoList;
    }
}
