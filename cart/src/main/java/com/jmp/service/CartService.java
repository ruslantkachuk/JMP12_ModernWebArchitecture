package com.jmp.service;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.entity.cart.Cart;
import com.jmp.entity.cart.CartItem;
import com.jmp.repository.CartItemRepository;
import com.jmp.repository.CartRepository;

@Service
public class CartService {

    private Logger logger = Logger.getLogger(CartService.class.getName());

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartById(Integer id) {
        logger.info("Get cart by id: " + id);
        Cart cart;
        if (id == 0){
            logger.info("Create new cart");
            cart = cartRepository.save(new Cart());
            logger.info("New cart id: " + cart.getId());
        } else {
            cart = cartRepository.findOne(id);
        }
        return cart;
    }

    @Transactional
    public Cart addProductToCart(Integer idCart, CartItem cartItem) {
        logger.info("Update cart by id: " + idCart);
        cartItem.setTotalPrice(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        boolean needCreateItem = true;
        Cart cart;
        if (idCart == 0){
            logger.info("Create new cart");
            cart = cartRepository.save(new Cart());
            logger.info("New cart id: " + cart.getId());
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
        } else {
            cart = cartRepository.findOne(idCart);
            for (CartItem item: cart.getCartItems()){
                if (item.getProduct().getId().equals(cartItem.getProduct().getId())){
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    item.setTotalPrice(item.getTotalPrice().add(cartItem.getTotalPrice()));
                    needCreateItem = false;
                    break;
                }
            }
            if (needCreateItem){
                cartItem.setCart(cart);
                cart.getCartItems().add(cartItem);
            }
        }
        return cart;
    }
}
