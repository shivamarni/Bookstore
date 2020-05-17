package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.PaginationServiceImpl;

@RestController
@RequestMapping("/pagination")
public class PaginationController {
	@Autowired
   private PaginationServiceImpl pageImpl;
 
    @GetMapping
    public ResponseEntity<Response> getAllEmployees(@RequestParam(defaultValue = "1") Integer pageNo, 
                        @RequestParam(defaultValue = "10") Integer pageSize)
                        //@RequestParam(defaultValue = "bookId") String sortBy) 
    {
        List<Book> books = pageImpl.getAllBooks(pageNo, pageSize);
 
        return new ResponseEntity<Response>(new Response("pagination",books ,200), HttpStatus.OK); 
    }
}
