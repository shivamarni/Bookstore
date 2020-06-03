package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.QuantityDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface QuantityService {


	
	Book mapQuantityToBook(Long bookId, QuantityDto quantityDto, String token) throws BookStoreException;
	Book incrementQuantity(Long bookId, String token) throws BookStoreException;
	Book decrementQuantity(Long bookId, String token) throws BookStoreException;
}
