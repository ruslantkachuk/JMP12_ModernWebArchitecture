package com.jmp.repository;

import org.springframework.data.repository.CrudRepository;

import com.jmp.entity.catalog.Catalog;

public interface CatalogRepository extends CrudRepository<Catalog, Integer> {

}
