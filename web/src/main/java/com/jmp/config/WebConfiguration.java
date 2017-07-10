package com.jmp.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.jmp.service.WebAccountService;
import com.jmp.service.WebCartService;
import com.jmp.service.WebCatalogService;

public class WebConfiguration {

    public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNT-APPLICATION";
    public static final String CATALOG_SERVICE_URL = "http://CATALOG-APPLICATION";
    public static final String CART_SERVICE_URL = "http://CART-APPLICATION";

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WebAccountService accountService() {
        return new WebAccountService(ACCOUNTS_SERVICE_URL);
    }

    @Bean
    public WebCatalogService catalogService() {
        return new WebCatalogService(CATALOG_SERVICE_URL);
    }

    @Bean
    public WebCartService cartService() {
        return new WebCartService(CART_SERVICE_URL);
    }
}
