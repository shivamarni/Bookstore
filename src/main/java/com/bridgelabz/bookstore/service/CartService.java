package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface CartService {

	List<Book> addBookToCart(Long bookId,String token) throws BookStoreException;
	Book updateBookQuantityInCart(Long bookId,String token);
	Book getCartBook(Long bookId, String token) throws BookStoreException;
	List<Book> getAllCartBooks(String token) throws BookStoreException;
	void deleteAllCartBooks(String token) throws BookStoreException;
	List<Book> deleteCartBook(Long bookId, String token) throws BookStoreException;
}
