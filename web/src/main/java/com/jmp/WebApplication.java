package com.jmp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import com.jmp.config.WebConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@Import(WebConfiguration.class)
@ComponentScan(excludeFilters = @ComponentScan.Filter(value = Service.class, type = FilterType.ANNOTATION))
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
