package com.jmp.service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.jmp.entity.catalog.Catalog;
import com.jmp.repository.CatalogRepository;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    private Logger logger = Logger.getLogger(CatalogService.class.getName());


    public List<Catalog> getCatalogs() {
        logger.info("Get all catalogs");
        return Lists.newArrayList(catalogRepository.findAll());
    }

    public Catalog getCatalogById(Integer id) {
        logger.info("Get all catalog by id: " + id);
        return catalogRepository.findOne(id);
    }

    public Catalog saveCatalog(Catalog catalog) {
        if(Objects.nonNull(catalog.getId())){
            logger.info("Update catalog: " + catalog.getId());
        } else {
            logger.info("Create new catalog");
        }
        return catalogRepository.save(catalog);
    }

    public void deleteCatalog(Integer id) {
        logger.info("Delete catalog by id: " + id);
        catalogRepository.delete(id);
    }
}
