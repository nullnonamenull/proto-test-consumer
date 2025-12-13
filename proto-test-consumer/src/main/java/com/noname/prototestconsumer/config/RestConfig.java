package com.noname.prototestconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080/producer")
                .configureMessageConverters((HttpMessageConverters.ClientBuilder converters) ->
                        converters
                                .registerDefaults()
                                .addCustomConverter(new ProtobufHttpMessageConverter())
                )
                .build();
    }

}