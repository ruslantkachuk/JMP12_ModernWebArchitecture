package com.jmp.service;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jmp.dto.CartItemDto;
import com.jmp.entity.cart.Cart;
import com.jmp.entity.cart.CartItem;
import com.jmp.entity.catalog.Product;

@Service
public class WebCartService {

    @Autowired
    private RestTemplate restTemplate;

    private String serviceUrl;

    private Logger logger = Logger.getLogger(WebCartService.class.getName());

    public WebCartService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    @PostConstruct
    public void demoOnly() {
        logger.warning("The RestTemplate request factory is " + restTemplate.getRequestFactory().getClass());
    }

    public Cart getCart(String idCart) {
        logger.info("Get current cart");
        Cart cart = restTemplate.getForObject(serviceUrl + "/carts/" + idCart, Cart.class);
        if (idCart.equals("0")) {
            getCurrentResponse().addCookie(new Cookie("idCart", String.valueOf(cart.getId())));
        }
        return cart;
    }

    public void addProductToCart(String idCart, CartItemDto cartItemDto) {
        logger.info("Add product to cart");
        CartItem cartItem = new CartItem();
        Product product = new Product();
        product.setId(cartItemDto.getIdProduct());
        cartItem.setProduct(product);
        BeanUtils.copyProperties(cartItemDto, cartItem);
        Cart cart = restTemplate.postForEntity(serviceUrl + "/carts/" + idCart, cartItem, Cart.class);
        if (idCart.equals("0")){
            getCurrentResponse().addCookie(new Cookie("idCart", String.valueOf(cart.getId())));
        }
    }

    private HttpServletResponse getCurrentResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
