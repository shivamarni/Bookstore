package com.bridgelabz.bookstore.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Override
	public List<Book> addBookToCart(Long bookId, String token) {
		
		return null;
	}

	@Override
	public List<Book> deleteBookFromCart(Long bookId, String token) {
		return null;
	}

	@Override
	public void deleteAllBooksFromCart(String token) {
		
	}

	@Override
	public Book updateBookQuantityInCart(Long bookId, String token) {
		return null;
	}

}
