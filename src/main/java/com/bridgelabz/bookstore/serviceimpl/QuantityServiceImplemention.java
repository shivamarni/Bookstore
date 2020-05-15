package com.bridgelabz.bookstore.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.QuantityDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.service.QuantityService;
import com.bridgelabz.bookstore.serviceimpl.BookServiceImpl;
@Service
public class QuantityServiceImplemention implements QuantityService {
	@Autowired
	private BookRepository bookrepo;

	@Autowired
	private QuantityRepository quantityrepo;

	@Autowired
	private BookServiceImpl bookService;

	@Override
	@Transactional
	public Quantity mapQuantityToBook(Long bookId, QuantityDto quantityDto) throws BookStoreException {

		Book book = bookService.getBookById(bookId);
		Quantity quantity = new Quantity();
		BeanUtils.copyProperties(quantityDto, quantity);
		if (book.getNoOfBooks() > quantity.getCartQuantity()) {
			
			
			Long updatedBookQuantity = (book.getNoOfBooks() - quantity.getCartQuantity());
			book.setNoOfBooks(updatedBookQuantity);
			boolean stockStaus=quantityrepo.updateBookStock(bookId, quantity.getCartQuantity());
			
		}

		else {
			throw new BookStoreException("book not available", HttpStatus.NOT_ACCEPTABLE);
		}

		return quantity;

	}

	@Override
	@Transactional
	public Quantity incrementQuantity(Long bookId, QuantityDto quantityDto) throws BookStoreException {
		Book book = bookService.getBookById(bookId);
		Quantity quantity = new Quantity();
		BeanUtils.copyProperties(quantityDto, quantity);
		if (book.getNoOfBooks() > 0) {
			Long cartQuantity = quantity.getCartQuantity() + 1;
			quantity.setCartQuantity(cartQuantity);
			book.setQuantity(quantity);
			quantity.setBook(book);
			Long updatedBookQuantity = (book.getNoOfBooks() - 1);
			book.setNoOfBooks(updatedBookQuantity);
			quantityrepo.save(quantity);
			bookrepo.save(book);

		}

		else {
			throw new BookStoreException("book stock complete ", HttpStatus.NOT_ACCEPTABLE);
		}
		return quantity;
	}

	@Override
	@Transactional
	public Quantity decrementQuantity(Long bookId, QuantityDto quantityDto) throws BookStoreException {
		Book book = bookService.getBookById(bookId);
		Quantity quantity = new Quantity();
		BeanUtils.copyProperties(quantityDto, quantity);

		Long cartQuantity = quantity.getCartQuantity() - 1;
		quantity.setCartQuantity(cartQuantity);
		book.setQuantity(quantity);
		quantity.setBook(book);
		Long updatedBookQuantity = (book.getNoOfBooks() + 1);
		book.setNoOfBooks(updatedBookQuantity);
		quantityrepo.save(quantity);
		bookrepo.save(book);

		return quantity;
	}

}
