package com.algaworks.algashop.ordering.infrastructure.adapters.output.web.shipping.client.rapidex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.net.http.HttpClient;

@Configuration
public class RapiDexAPIClientConfig {

    @Bean
    public RapiDexAPIClient rapiDexApiClient(@Value("${algashop.integrations.rapidex.url}") String rapiDexUrl) {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        JdkClientHttpRequestFactory jdkClientHttpRequestFactory = new JdkClientHttpRequestFactory(httpClient);

        RestClient restClient = RestClient.builder()
                .requestFactory(jdkClientHttpRequestFactory)
                .baseUrl(rapiDexUrl)
                .build();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        return proxyFactory.createClient(RapiDexAPIClient.class);
    }
}
