package com.jmp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.entity.catalog.Catalog;
import com.jmp.service.CatalogService;

@RestController
@RequestMapping(value = "/catalogs")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Catalog> getCatalogs() {
        return catalogService.getCatalogs();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Catalog getCatalogById(@PathVariable("id") Integer id) {
        return catalogService.getCatalogById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Catalog updateCatalog(@RequestBody Catalog catalog) {
        return catalogService.saveCatalog(catalog);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Catalog createCatalog(@RequestBody Catalog catalog) {
        return catalogService.saveCatalog(catalog);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void updateCatalogById(@PathVariable("id") Integer id) {
        catalogService.deleteCatalog(id);
    }

}

