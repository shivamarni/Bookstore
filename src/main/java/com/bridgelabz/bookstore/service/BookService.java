package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface BookService {
	public Book addBook(BookDto bookDTO, String token) throws BookStoreException;

	public Book updateBook(BookDto bookDTO, String token, Long bookId) throws BookStoreException;

	public Book deleteBook(String token, Long bookId) throws BookStoreException;

	public List<Book> getBooksSortedByPriceLow(String token) throws BookStoreException;

	public List<Book> getBooksSortedByPriceHigh(String token) throws BookStoreException;

	public List<Book> getBooksSortedByArrival(String token) throws BookStoreException;

	public Book getBookById(Long bookId) throws BookStoreException;

	public List<Book> getAllSellerBooks(String token) throws BookStoreException;

	public List<Book> getAllBooks() throws BookStoreException;

}
