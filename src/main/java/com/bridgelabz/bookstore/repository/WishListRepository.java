package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.entity.WishList;

public interface WishListRepository extends JpaRepository<WishList,String> {

}
