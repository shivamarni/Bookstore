package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.CartServiceImpl;

@RestController
public class CartController {
	
	@Autowired
	private CartServiceImpl cartImpl;
	
	@PostMapping("cart/addbooktocart/{bookId}")
	public ResponseEntity<Response> addBookToCart(@PathVariable("bookId") Long bookId,@RequestHeader String token)
	{
		return new ResponseEntity<Response>(new Response("book added to cart", null, 200),HttpStatus.OK);
	}
}
