package com.bridgelabz.bookstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.exception.S3BucketException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.response.S3Response;
import com.bridgelabz.bookstore.service.AmazonS3ClientService;
import com.bridgelabz.bookstore.service.BookService;
import com.bridgelabz.bookstore.serviceimpl.AmazonS3ClientServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(description="Book Store details")
@RequestMapping("book")

public class BookController {

	@Autowired
	private BookService bookService;
	@PostMapping(value="/add")
	@ApiOperation(value = "add book details",response = Iterable.class)
	public ResponseEntity<Response> addBook( @RequestParam(value = "file") MultipartFile file,@RequestBody BookDto bookDTO, @RequestHeader(name = "token") String token)
			throws BookStoreException, S3BucketException {
		System.out.println(file);
		Book book = bookService.addBook(file,bookDTO, token);
		return new ResponseEntity<Response>(new Response("Book added to seller", book, 200), HttpStatus.CREATED);
	}

	@PostMapping("/updateBook/{bookId}")
	@ApiOperation(value = "update book details",response = Iterable.class)
	public ResponseEntity<Response> sellerUpdateBook(@PathVariable("bookId") Long bookId,@RequestBody Book bookDetails,@RequestHeader("token") String token) throws BookStoreException {
		System.out.println(bookDetails);
		System.out.println(bookId);
		Book book = bookService.updateBook(bookDetails,token, bookId);
		return new ResponseEntity<Response>(new Response("Book updated to seller", book, 200), HttpStatus.CREATED);

	}
	
	@PostMapping("/verify")
	@ApiOperation(value = "update book details",response = Iterable.class)
	public ResponseEntity<Response> verifyBook(@RequestHeader("bookId") Long bookId) throws BookStoreException {
		Book book = bookService.verifyBook(bookId);
		return new ResponseEntity<Response>(new Response("Book verified", book, 200), HttpStatus.CREATED);

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
	
	@GetMapping("/getbookcount")
	@ApiOperation(value = "get books",response = Iterable.class)
	public ResponseEntity<Response> getBookCount() throws BookStoreException {
		int noOfBooks = bookService.getBookCount();
		return new ResponseEntity<Response>(new Response("number of books",noOfBooks, 200), HttpStatus.CREATED);

	}
	
	@GetMapping("/getallbooks/")
	@ApiOperation(value = "get books",response = Iterable.class)
	public ResponseEntity<Response> getBookById() throws BookStoreException {
		List<Book> allBooks = bookService.getAllBooks();
		return new ResponseEntity<Response>(new Response("book details", allBooks, 200), HttpStatus.CREATED);

	}
	
//	@PostMapping("files/add")
//	public ResponseEntity<S3Response> uploadFile(@RequestPart(value = "file") MultipartFile file,
//			@RequestHeader boolean enablePublicReadAccess) throws S3BucketException {
//		this.amazonS3ClientService.uploadFileToS3Bucket(file, enablePublicReadAccess);
//
//		Map<String, String> responseMap = new HashMap<>();
//		responseMap.put("message",
//				"file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");
//
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new S3Response("Image added successfully", 200));
//	}
//
//	@DeleteMapping("files/delete")
//	public ResponseEntity<S3Response> deleteFile(@RequestParam("file_name") String fileName) throws S3BucketException {
//		this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
//
//		Map<String, String> response = new HashMap<>();
//		response.put("message", "file [" + fileName + "] removing request submitted successfully.");
//
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new S3Response("Image deleted successfully", 200));
//	}

}
