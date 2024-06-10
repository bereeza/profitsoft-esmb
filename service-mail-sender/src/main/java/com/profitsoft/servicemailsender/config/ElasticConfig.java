package com.profitsoft.servicemailsender.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

/**
 * Elasticsearch config file
 */
@Configuration
public class ElasticConfig extends ElasticsearchConfiguration {

    @Value("${elasticsearch.address}")
    private String elasticsearchAddress;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticsearchAddress)
                .build();
    }
}
