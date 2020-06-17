package com.bridgelabz.bookstore.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.OrderServiceImpl;

import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("order")
public class OrderController {
	@Autowired
	private OrderServiceImpl orderImpl;
	
	@PostMapping("/addorder")
	public ResponseEntity<Response> addOrder(@RequestHeader String token,@RequestHeader Long totalCartPrice,@RequestHeader Long deliveryCharges,@RequestHeader String type) throws BookStoreException, MessagingException, IOException
	{
		Order order=orderImpl.addOrder(token,totalCartPrice,deliveryCharges,type);
		return new ResponseEntity<Response>(new Response("your all orders are",order, 200),HttpStatus.OK);
	}
}
