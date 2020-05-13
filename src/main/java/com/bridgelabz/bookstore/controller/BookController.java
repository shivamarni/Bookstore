package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.BookService;

public class BookController {
	
	@Autowired
	
	private BookService bookService;
	
	@PostMapping("book/add")
	public ResponseEntity<Response> addBook(@RequestBody BookDto bookDTO,@RequestHeader(name="token") String token){
		bookService.addBook(bookDTO, token);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(),env.getProperty("2001")));
	}
	

}
