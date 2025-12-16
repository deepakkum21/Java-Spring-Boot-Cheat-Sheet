package com.rest.client.rest_client.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(basePackages = "com.rest.client.rest_client.client",
        types = {ProductClient.class}, group = "product-client")
public class HttpClientConfig {
}