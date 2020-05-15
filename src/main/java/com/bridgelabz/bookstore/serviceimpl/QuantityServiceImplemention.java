package com.bridgelabz.bookstore.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
	@Modifying
	public Quantity mapQuantityToBook(Long bookId, QuantityDto quantityDto) throws BookStoreException {
		boolean flag=false;
		Book book = bookService.getBookById(bookId);
		Quantity quantity = new Quantity();
		BeanUtils.copyProperties(quantityDto, quantity);
		if (book.getNoOfBooks() > quantity.getCartQuantity()) {
			
			
			Long updatedBookQuantity = (book.getNoOfBooks() - quantity.getCartQuantity());
			book.setNoOfBooks(updatedBookQuantity);
			boolean stockStaus=quantityrepo.updateBookStock(bookId, quantity.getCartQuantity());
			
			
			List<Quantity> quantityList=quantityrepo.getAllQuantity();
			for(Quantity quant:quantityList)
			{
				if(quant.getBook().getBookId().equals(bookId))
				{
					flag=true;
					break;
				}
			}
			if(flag==false)
			{
				quantity.setBook(book);
				
				quantityrepo.save(quantity);
				book.setQuantity(quantity);
				bookrepo.save(book);
			}
			else {
				quantityrepo.updateBookStock(bookId, quantity.getCartQuantity());
			}
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
