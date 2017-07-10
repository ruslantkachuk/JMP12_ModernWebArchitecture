package com.jmp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmp.entity.cart.Cart;
import com.jmp.entity.cart.CartItem;
import com.jmp.service.CartService;

@RestController
@RequestMapping(value = "/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public Cart addProductToCart(@PathVariable("id") Integer id, @RequestBody CartItem cartItem) {
        return cartService.addProductToCart(id, cartItem);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Cart getCartById(@PathVariable("id") Integer id) {
        return cartService.getCartById(id);
    }

}

