package com.jmp.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jmp.entity.catalog.Catalog;

@Service
public class WebCatalogService {

    @Autowired
    private RestTemplate restTemplate;

    private String serviceUrl;

    private Logger logger = Logger.getLogger(WebCatalogService.class.getName());

    public WebCatalogService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    @PostConstruct
    public void demoOnly() {
        logger.warning("The RestTemplate request factory is " + restTemplate.getRequestFactory().getClass());
    }

    public List<Catalog> getCatalogs() {
        logger.info("Get all catalogs");
        Catalog[] catalogs = null;
        try {
            catalogs = restTemplate.getForObject(serviceUrl + "/catalogs", Catalog[].class);
        } catch (HttpClientErrorException e) {
            logger.warning("Get all catalogs "+e);
        }
        return Optional.ofNullable(catalogs).map(Arrays::asList).orElse(Collections.EMPTY_LIST);
    }

    public Catalog getCatalogById(String id) {
        logger.info("Get catalog by id: "+ id);
        return restTemplate.getForObject(serviceUrl + "/catalogs/"+id, Catalog.class);
    }


    public List<Catalog> deleteCatalog(String id) {
        logger.info("Delete catalog");
        restTemplate.delete(serviceUrl + "/catalogs/"+id);
        return getCatalogs();
    }

    public List<Catalog> createCatalog(Catalog catalog) {
        logger.info("Create catalog");
        restTemplate.postForObject(serviceUrl + "/catalogs", catalog, Catalog.class);
        return getCatalogs();
    }

    public List<Catalog> updateCatalog(Catalog catalog) {
        logger.info("Update catalog by id: " + catalog.getId());
        restTemplate.put(serviceUrl + "/catalogs/"+catalog.getId(), catalog);
        return getCatalogs();
    }

}
