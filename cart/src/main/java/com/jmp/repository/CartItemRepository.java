package com.jmp.repository;

import org.springframework.data.repository.CrudRepository;

import com.jmp.entity.cart.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {

}
