package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.QuantityDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.QuantityService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("quantity")
public class QuantityController {
	
	
	@Autowired
	QuantityService quantityService;
	@PostMapping("/mapquantitytobook/{bookId}")
	@ApiOperation(value = "adding books to cart",response = Iterable.class)
	public ResponseEntity<Response> mapQuantityToBook(@PathVariable("bookId") Long bookId,@RequestHeader String token,@RequestBody QuantityDto quantityDto) throws BookStoreException
	{
		Book book=quantityService.mapQuantityToBook(bookId, quantityDto,token);
		return new ResponseEntity<Response>(new Response("book quantity added to cart", book, 200),HttpStatus.OK);
	}
	
	
	@PostMapping("/incrementquantitytobook/{bookId}")
	@ApiOperation(value = "adding books to cart",response = Iterable.class)
	public ResponseEntity<Response> incrementQuantityToBook(@PathVariable("bookId") Long bookId,@RequestHeader String token) throws BookStoreException
	{
		Book book=quantityService.incrementQuantity(bookId, token);
		return new ResponseEntity<Response>(new Response("book quantity incremented by 1 in cart", book, 200),HttpStatus.OK);
	}
	
	@PostMapping("/decrementquantitytobook/{bookId}")
	@ApiOperation(value = "adding books to cart",response = Iterable.class)
	public ResponseEntity<Response> decrementQuantityToBook(@PathVariable("bookId") Long bookId,@RequestHeader String token,@RequestBody QuantityDto quantityDto) throws BookStoreException
	{
		Book book=quantityService.decrementQuantity(bookId, token);
		return new ResponseEntity<Response>(new Response("book added to cart", book, 200),HttpStatus.OK);
	}

}
