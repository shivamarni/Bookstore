package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;

public interface BookService {
	public Book addBook(BookDto bookDTO, String token);

}
