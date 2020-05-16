package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.OrderServiceImpl;

@RestController
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderServiceImpl orderImpl;
	
	@GetMapping("/getallorders")
	public ResponseEntity<Response> getAllOrders(@RequestHeader String token) throws BookStoreException
	{
		List<Book> books=orderImpl.getAllOrders(token);
		return new ResponseEntity<Response>(new Response("your all orders are",books, 200),HttpStatus.OK);
	}
}
