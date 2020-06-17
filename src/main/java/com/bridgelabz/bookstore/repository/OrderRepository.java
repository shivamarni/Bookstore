package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
	
	@Query(value="select * from ordereredbooks where order_id=?1",nativeQuery = true)
	Order getOrderById(Long orderId);

}
