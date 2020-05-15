package com.bridgelabz.bookstore.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.CartServiceImpl;

import io.swagger.annotations.Api;

@RestController
@Api(description=" Cart in Book Store")
@RequestMapping("cart")
public class CartController {
	
	@Autowired
	private CartServiceImpl cartImpl;
	@PostMapping("/addbooktocart/{bookId}")
	public ResponseEntity<Response> addBookToCart(@PathVariable("bookId") Long bookId,@RequestHeader String token) throws BookStoreException
	{
		List<Book> cartBooks=cartImpl.addBookToCart(bookId, token);
		return new ResponseEntity<Response>(new Response("book added to cart", cartBooks, 200),HttpStatus.OK);
	}
	
	@GetMapping("/allcartbooks")
	public ResponseEntity<Response> getAllCartBooks(@RequestHeader String token) throws BookStoreException
	{
		List<Book> cartBooks=cartImpl.getAllCartBooks(token);
		return new ResponseEntity<Response>(new Response("all books from cart", cartBooks, 200),HttpStatus.OK);
	}
	
	@GetMapping("/CartBook/{bookId}")
	public ResponseEntity<Response> getCartBook(@PathVariable("bookId") Long bookId,@RequestHeader String token) throws BookStoreException
	{
		Book book=cartImpl.getCartBook(bookId, token);
		return new ResponseEntity<Response>(new Response("book from cart   by id", book, 200),HttpStatus.OK);
	}
	
	@DeleteMapping("/allcartbooks")
	public ResponseEntity<Response> deleteAllCartBooks(@RequestHeader String token) throws BookStoreException
	{
		cartImpl.deleteAllCartBooks(token);
		return new ResponseEntity<Response>(new Response("all books deleted from cart",null, 200),HttpStatus.OK);
	}
	
	@DeleteMapping("/CartBook/{bookId}")
	public ResponseEntity<Response> deleteCartBook(@PathVariable("bookId") Long bookId,@RequestHeader String token) throws BookStoreException
	{
		List<Book> cartBooks=cartImpl.deleteCartBook(bookId,token);
		return new ResponseEntity<Response>(new Response("book from cart   by id", cartBooks, 200),HttpStatus.OK);
	}
}
