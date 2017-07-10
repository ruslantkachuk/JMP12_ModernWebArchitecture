package com.jmp.repository;

import org.springframework.data.repository.CrudRepository;

import com.jmp.entity.cart.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {

}
