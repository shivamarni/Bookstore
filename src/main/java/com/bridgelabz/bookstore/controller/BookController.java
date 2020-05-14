package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.BookService;

@RestController
public class BookController {

	@Autowired

	private BookService bookService;

	@PostMapping("book/add")
	public ResponseEntity<Response> addBook(@RequestBody BookDto bookDTO, @RequestHeader(name = "token") String token)
			throws BookStoreException {
		Book book = bookService.addBook(bookDTO, token);
		return new ResponseEntity<Response>(new Response("Book added to seller", book, 200), HttpStatus.CREATED);
	}

	@PostMapping("book/update/{bookId}")
	public ResponseEntity<Response> updateBook(@RequestBody BookDto bookDTO, @RequestHeader("token") String token,
			@PathVariable("bookId") Long bookId) throws BookStoreException {
		Book book = bookService.updateBook(bookDTO, token, bookId);
		return new ResponseEntity<Response>(new Response("Book updated to seller", book, 200), HttpStatus.CREATED);

	}

	@PostMapping("book/delete/{bookId}")
	public ResponseEntity<Response> deleteBook(@RequestHeader("token") String token,
			@PathVariable("bookId") Long bookId) throws BookStoreException {
		Book book = bookService.deleteBook(token, bookId);
		return new ResponseEntity<Response>(new Response("Book deleted to seller", book, 200), HttpStatus.CREATED);

	}

	@GetMapping("book/sortedbypricelow/{token}")
	public ResponseEntity<Response> sortedByPriceLow(@RequestHeader("token") String token) throws BookStoreException {
		List<Book> priceLowSortedList = bookService.getBooksSortedByPriceLow(token);
		return new ResponseEntity<Response>(new Response("sorted book by price low", priceLowSortedList, 200),
				HttpStatus.CREATED);

	}

	@GetMapping("book/sortedbypricehigh/{token}")
	public ResponseEntity<Response> sortedByPriceHigh(@RequestHeader("token") String token) throws BookStoreException {
		List<Book> priceHighSortedList = bookService.getBooksSortedByPriceHigh(token);
		return new ResponseEntity<Response>(new Response("sorted book by price high", priceHighSortedList, 200),
				HttpStatus.CREATED);

	}

	@GetMapping("book/sortedbyarrival/{token}")
	public ResponseEntity<Response> sortedByArrival(@RequestHeader("token") String token) throws BookStoreException {
		List<Book> arrivalSortedList = bookService.getBooksSortedByPriceHigh(token);
		return new ResponseEntity<Response>(new Response("sorted book by price high", arrivalSortedList, 200),
				HttpStatus.CREATED);

	}

	@GetMapping("book/getbookbyid/{bookId}")
	public ResponseEntity<Response> getBookById(@PathVariable("bookId") Long bookId) throws BookStoreException {
		Book book = bookService.getBookById(bookId);
		return new ResponseEntity<Response>(new Response("book details", book, 200), HttpStatus.CREATED);

	}

}
