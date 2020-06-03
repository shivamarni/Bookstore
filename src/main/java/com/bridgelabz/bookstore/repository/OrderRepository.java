package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
