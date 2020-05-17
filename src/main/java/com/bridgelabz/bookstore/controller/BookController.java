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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="Book Store details")
@RequestMapping("book")
public class BookController {

	@Autowired

	private BookService bookService;

	@PostMapping("/add")
	@ApiOperation(value = "add book details",response = Iterable.class)
	public ResponseEntity<Response> addBook(@RequestBody BookDto bookDTO, @RequestHeader(name = "token") String token)
			throws BookStoreException {
		Book book = bookService.addBook(bookDTO, token);
		return new ResponseEntity<Response>(new Response("Book added to seller", book, 200), HttpStatus.CREATED);
	}

	@PostMapping("/update/{bookId}")
	@ApiOperation(value = "update book details",response = Iterable.class)
	public ResponseEntity<Response> updateBook(@RequestBody BookDto bookDTO, @RequestHeader("token") String token,
			@PathVariable("bookId") Long bookId) throws BookStoreException {
		Book book = bookService.updateBook(bookDTO, token, bookId);
		return new ResponseEntity<Response>(new Response("Book updated to seller", book, 200), HttpStatus.CREATED);

	}

	@PostMapping("/delete/{bookId}")
	@ApiOperation(value = "delete book details",response = Iterable.class)
	public ResponseEntity<Response> deleteBook(@RequestHeader("token") String token,
			@PathVariable("bookId") Long bookId) throws BookStoreException {
		Book book = bookService.deleteBook(token, bookId);
		return new ResponseEntity<Response>(new Response("Book deleted to seller", book, 200), HttpStatus.CREATED);

	}

	@GetMapping("/sortedbypricelow/{token}")
	@ApiOperation(value = "book details sorted by price low-higher order",response = Iterable.class)
	public ResponseEntity<Response> sortedByPriceLow(@RequestHeader("token") String token) throws BookStoreException {
		List<Book> priceLowSortedList = bookService.getBooksSortedByPriceLow(token);
		return new ResponseEntity<Response>(new Response("sorted book by price low", priceLowSortedList, 200),
				HttpStatus.CREATED);

	}

	@GetMapping("/sortedbypricehigh/{token}")
	@ApiOperation(value = "book details sorted by price in high-lower order",response = Iterable.class)
	public ResponseEntity<Response> sortedByPriceHigh(@RequestHeader("token") String token) throws BookStoreException {
		List<Book> priceHighSortedList = bookService.getBooksSortedByPriceHigh(token);
		return new ResponseEntity<Response>(new Response("sorted book by price high", priceHighSortedList, 200),
				HttpStatus.CREATED);

	}

	@GetMapping("/sortedbyarrival/{token}")
	@ApiOperation(value = "book details sorted by arrival",response = Iterable.class)
	public ResponseEntity<Response> sortedByArrival(@RequestHeader("token") String token) throws BookStoreException {
		List<Book> arrivalSortedList = bookService.getBooksSortedByPriceHigh(token);
		return new ResponseEntity<Response>(new Response("sorted book by price high", arrivalSortedList, 200),
				HttpStatus.CREATED);

	}

	@GetMapping("/getbookbyid/{bookId}")
	@ApiOperation(value = "get books",response = Iterable.class)
	public ResponseEntity<Response> getBookById(@PathVariable("bookId") Long bookId) throws BookStoreException {
		Book book = bookService.getBookById(bookId);
		return new ResponseEntity<Response>(new Response("book details", book, 200), HttpStatus.CREATED);

	}
	
	@GetMapping("/getAllBooks/")
	@ApiOperation(value = "get books",response = Iterable.class)
	public ResponseEntity<Response> getBookById() throws BookStoreException {
		List<Book> allBooks = bookService.getAllBooks();
		return new ResponseEntity<Response>(new Response("book details", allBooks, 200), HttpStatus.CREATED);

	}

}
