package com.jmp.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jmp.entity.cart.CartItem;
import com.jmp.entity.catalog.Catalog;
import com.jmp.service.WebCatalogService;

@Controller
public class WebCatalogController {

	private WebCatalogService catalogService;

	@Autowired
	public WebCatalogController(WebCatalogService catalogService) {
		this.catalogService = catalogService;
	}

	private Logger logger = Logger.getLogger(WebCatalogController.class.getName());

	@RequestMapping("/catalogs")
	public String getCatalogs(Model model) {
		logger.info("catalog-service call");
		List<Catalog> catalogs = catalogService.getCatalogs();
		logger.info("catalog-service found: " + catalogs);
		populateModel(model, catalogs);
		return "catalogs";
	}

	@RequestMapping(value = "/catalogs/{id}", method = RequestMethod.GET)
	public String getCatalogById(Model model, @PathVariable("id") String id) {
		logger.info("catalog-service call by id: "+ id);
		Catalog catalog = catalogService.getCatalogById(id);
		logger.info("catalog-service found: " + catalog);
		model.addAttribute("catalog", catalog);
		model.addAttribute("cartItem", new CartItem());
		return "catalog";
	}

	@RequestMapping(value = "/catalogs/{id}/update", method = RequestMethod.GET)
	public String updateCatalog(Model model, @PathVariable("id") String id) {
		Catalog catalog = catalogService.getCatalogById(id);
		model.addAttribute("catalog", catalog);
		return "catalogUpdate";
	}


	@RequestMapping(value = "/catalogs", method = RequestMethod.PUT)
	public String updateCatalog(Model model, @ModelAttribute("catalog") Catalog catalog) {
		List<Catalog> catalogs = catalogService.updateCatalog(catalog);
		populateModel(model, catalogs);
		return "catalogs";
	}

	@RequestMapping(value = "/catalogs", method = RequestMethod.POST)
	public String createCatalog(Model model, @ModelAttribute("catalog") Catalog catalog) {
		List<Catalog> catalogs = catalogService.createCatalog(catalog);
		populateModel(model, catalogs);
		return "catalogs";
	}

	@RequestMapping(value = "/catalogs/{id}", method = RequestMethod.DELETE)
	public String deleteCatalog(Model model, @PathVariable("id") String id) {
		List<Catalog> catalogs = catalogService.deleteCatalog(id);
		populateModel(model, catalogs);
		return "catalogs";
	}

	private void populateModel(Model model, List<Catalog> catalogs){
		model.addAttribute("catalogs", catalogs);
		model.addAttribute("catalog", new Catalog());
	}
}
