package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.entity.Book;

public interface CartService {

	List<Book> addBookToCart(Long bookId,String token);
	List<Book> deleteBookFromCart(Long bookId,String token);
	void deleteAllBooksFromCart(String token);
	Book updateBookQuantityInCart(Long bookId,String token);
}
