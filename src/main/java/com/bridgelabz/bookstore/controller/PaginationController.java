package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.PaginationServiceImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pagination")
public class PaginationController {
	@Autowired
   private PaginationServiceImpl pageImpl;
 
    @GetMapping("/booksbyid")
    public ResponseEntity<Response> getBooks(@RequestHeader(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "8") Integer pageSize,
                        @RequestParam(defaultValue = "bookId") String sortBy)throws BookStoreException
    {
        List<Book> books = pageImpl.getBooksById(pageNo, pageSize , sortBy);
 
        return new ResponseEntity<Response>(new Response("pagination",books ,200), HttpStatus.OK); 
    }
    
    @GetMapping("/sellerbooks")
    public ResponseEntity<Response> getSellerBooks(@RequestHeader(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "8") Integer pageSize,
                        @RequestParam(defaultValue = "bookId") String sortBy,@RequestHeader String token)throws BookStoreException
    {
        List<Book> books = pageImpl.getSellerBooks(pageNo, pageSize , sortBy,token);
 
        return new ResponseEntity<Response>(new Response("pagination",books ,200), HttpStatus.OK); 
    }
    
    @GetMapping("/booksbyprice/descending")
    public ResponseEntity<Response> getBooksByPriceDescending(@RequestHeader(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "8") Integer pageSize,
                        @RequestParam(defaultValue = "bookPrice") String sortBy)throws BookStoreException
    {
        List<Book> books = pageImpl.getBooksByPriceDescending(pageNo, pageSize , sortBy);
 
        return new ResponseEntity<Response>(new Response("pagination",books ,200), HttpStatus.OK); 
    }
    
    @GetMapping("/booksbyprice/ascending")
    public ResponseEntity<Response> getBooksByPriceAscending(@RequestHeader(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "8") Integer pageSize,
                        @RequestParam(defaultValue = "bookPrice") String sortBy)throws BookStoreException
    {
        List<Book> books = pageImpl.getBooksByPriceAscending(pageNo, pageSize , sortBy);
 
        return new ResponseEntity<Response>(new Response("pagination",books ,200), HttpStatus.OK); 
    }
    
    @GetMapping("/booksbyarrival")
    public ResponseEntity<Response> getBooksByArrivel(@RequestHeader
    		(defaultValue = "0") Integer pageNo, 
                        @RequestParam(defaultValue = "8") Integer pageSize,
                        @RequestParam(defaultValue = "bookAddedTime") String sortBy)throws BookStoreException
    {
        List<Book> books = pageImpl.getBooksByArrivel(pageNo, pageSize , sortBy);
 
        return new ResponseEntity<Response>(new Response("pagination",books ,200), HttpStatus.OK); 
    }
    
    
}
