package com.adrrriannn.store.indexing.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfiguration {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Bean
    public RestHighLevelClient esClient() {
        return new RestHighLevelClient(RestClient.builder(HttpHost.create(elasticsearchHost)));
    }
}
