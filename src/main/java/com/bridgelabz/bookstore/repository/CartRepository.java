package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {

}
