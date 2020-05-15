package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface WishListService {

	List<Book> addBookToWishList(Long bookId,String token) throws BookStoreException;
	List<Book> deleteBookFromWishList(Long bookId,String token);
	void deleteAllBooksFromWishList(String token);
	Book updateBookQuantityInWishList(Long bookId,String token);
	Book getWishListBook(Long bookId, String token) throws BookStoreException;
	List<Book> getAllWishListBooks(String token) throws BookStoreException;
	void deleteAllWishListBooks(String token) throws BookStoreException;
	List<Book> deleteWishListBook(Long bookId, String token) throws BookStoreException;
}
