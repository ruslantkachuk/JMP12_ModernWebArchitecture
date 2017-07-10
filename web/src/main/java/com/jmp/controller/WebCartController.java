package com.jmp.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jmp.dto.CartItemDto;
import com.jmp.entity.cart.Cart;
import com.jmp.entity.catalog.Catalog;
import com.jmp.service.WebCartService;
import com.jmp.service.WebCatalogService;

@Controller
public class WebCartController {

	private WebCatalogService webCatalogService;
	private WebCartService webCartService;

	@Autowired
	public WebCartController(WebCartService webCartService, WebCatalogService webCatalogService) {
		this.webCartService = webCartService;
		this.webCatalogService = webCatalogService;
	}

	private Logger logger = Logger.getLogger(WebCartController.class.getName());

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String getCart(Model model, @CookieValue(value = "idCart", defaultValue = "0") String idCart) {
		logger.info("cart-service call");
		Cart cart = webCartService.getCart(idCart);
		model.addAttribute("cart", cart);
		return "cart";
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public String addProductToCart( Model model,
								  @CookieValue(value = "idCart", defaultValue = "0") String idCart,
								  @ModelAttribute("cartItem") CartItemDto cartItemDto) {
		logger.info("cart-service add product to cart");
		webCartService.addProductToCart(idCart, cartItemDto);
		List<Catalog> catalogs = webCatalogService.getCatalogs();
		model.addAttribute("catalogs", catalogs);
		model.addAttribute("catalog", new Catalog());
		return "catalogs";
	}
}
