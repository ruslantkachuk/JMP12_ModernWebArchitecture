package com.jmp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@EntityScan(basePackages = "com.jmp.entity.catalog")
@EnableJpaRepositories("com.jmp.repository")
public class CatalogConfiguration {

}
