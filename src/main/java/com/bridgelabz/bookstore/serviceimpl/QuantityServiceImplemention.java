
package com.bridgelabz.bookstore.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.QuantityDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.service.QuantityService;
import com.bridgelabz.bookstore.serviceimpl.BookServiceImpl;
import com.bridgelabz.bookstore.utility.JWTUtility;
@Service
public class QuantityServiceImplemention implements QuantityService {
	@Autowired
	private QuantityRepository quantityrepo;

	@Autowired
	private BookServiceImpl bookService;
	
	@Autowired
	private CartServiceImpl cartImpl;
	@Autowired
	private UserServiceImpl userImpl;
	@Autowired
	private BookServiceImpl bookImpl;
	@Autowired
	private BookRepository bookrepo;
	@Autowired
	private QuantityRepository quantrepo;
	@Override
	@Transactional
	public Book mapQuantityToBook(Long bookId, QuantityDto quantityDto,String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Cart cart=user.getCart();
		Book book = cart.getBooklist().stream().filter(boook -> boook.getBookId().equals(bookId)).findFirst()
				.orElseThrow(() -> new BookStoreException("Book not present in the cart ", HttpStatus.NOT_FOUND));
		if(book.getNoOfBooks()-quantityDto.getCartQuantity()>=0)
		{
			Long result=quantityrepo.isBookQuantMapped(bookId);
			if(result!=null)
			{
				quantrepo.alterQuantity(quantityDto.getCartQuantity(),bookId);
				book.setNoOfBooks(book.getNoOfBooks()-quantityDto.getCartQuantity());
				Book book2=bookrepo.save(book);
				return book2;
			}
			else {
			Quantity quantity=new Quantity();
			quantity.setCartQuantity(quantityDto.getCartQuantity());
			book.setNoOfBooks(book.getNoOfBooks()-quantityDto.getCartQuantity());
			quantity.setBook(book);
			book.setQuantity(quantity);
			quantrepo.save(quantity);
			bookrepo.save(book);
			return book;
			}
		}
		else
			throw new BookStoreException("book stock not avaliable",HttpStatus.BAD_REQUEST);
	}
	
	@Override
	@Transactional
	public Book incrementQuantity(Long bookId,String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Cart cart=user.getCart();
		Book book = cart.getBooklist().stream().filter(boook -> boook.getBookId().equals(bookId)).findFirst()
				.orElseThrow(() -> new BookStoreException("Book not present in the cart ", HttpStatus.NOT_FOUND));
		Quantity quantity=book.getQuantity();
		if(book.getNoOfBooks()-1!=0)
		{
			Long result=quantityrepo.isBookQuantMapped(bookId);
			if(result!=null)
			{
				quantrepo.alterQuantity(quantity.getCartQuantity()+1,bookId);
				book.setNoOfBooks(book.getNoOfBooks()-1);
				Book book2=bookrepo.save(book);
				return book2;
			}
			else {
			quantity.setCartQuantity(quantity.getCartQuantity()+1);
			book.setNoOfBooks(book.getNoOfBooks()-1);
			quantity.setBook(book);
			book.setQuantity(quantity);
			quantrepo.save(quantity);
			Book book2=bookrepo.save(book);
			return book2;
			}
		}
		else
			throw new BookStoreException("book stock not avaliable",HttpStatus.BAD_REQUEST);
	}

	@Override
	@Transactional
	@Modifying
	public Book decrementQuantity(Long bookId,String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Cart cart=user.getCart();
		Book book = cart.getBooklist().stream().filter(boook -> boook.getBookId().equals(bookId)).findFirst()
				.orElseThrow(() -> new BookStoreException("Book not present in the cart ", HttpStatus.NOT_FOUND));
		Quantity quantity=book.getQuantity();
		if(book.getNoOfBooks()!=1)
		{
			Long result=quantityrepo.isBookQuantMapped(bookId);
			if(result!=null)
			{
				quantrepo.alterQuantity(quantity.getCartQuantity()-1,bookId);
				book.setNoOfBooks(book.getNoOfBooks()+1);
				Book book2=bookrepo.save(book);
				return book2;
			}
			else {
			quantity.setCartQuantity(quantity.getCartQuantity()-1);
			book.setNoOfBooks(book.getNoOfBooks()+1);
			quantity.setBook(book);
			book.setQuantity(quantity);
			quantrepo.save(quantity);
			Book book2=bookrepo.save(book);
			return book2;
			}
		}
		else
			throw new BookStoreException("Atleast one book should buy",HttpStatus.BAD_REQUEST);
	}

	
//	@Autowired
//	private BookRepository bookrepo;
//
//	@Autowired
//	private QuantityRepository quantityrepo;
//
//	@Autowired
//	private BookServiceImpl bookService;
	
//	@Override
//	@Transactional
//
//	public Quantity mapQuantityToBook(Long bookId, QuantityDto quantityDto) throws BookStoreException {
//
//		boolean flag=false;
//		Book book = bookService.getBookById(bookId);
//		Quantity quantity = new Quantity();
//		BeanUtils.copyProperties(quantityDto, quantity);
//		if (book.getNoOfBooks() >= quantity.getCartQuantity()) {
//			
//			
//			Long updatedBookQuantity = (book.getNoOfBooks() - quantity.getCartQuantity());
//			book.setNoOfBooks(updatedBookQuantity);
//			boolean stockStaus=quantityrepo.updateBookStock(bookId, quantity.getCartQuantity());
//			quantityrepo.save(quantity);
//			bookrepo.save(book);
//			
//			
//			List<Quantity> quantityList=quantityrepo.getAllQuantity();
//			System.out.println(quantityList);
//			for(Quantity quant:quantityList)
//			{
//				if(quant.getBook().getBookId().equals(bookId) || quant==null)
//				{
//					flag=true;
//					break;
//				}
//			}
//			if(flag==false)
//			{
//				quantity.setBook(book);
//				
//				quantityrepo.save(quantity);
//				book.setQuantity(quantity);
//				bookrepo.save(book);
//			}
//			else {
//				quantityrepo.updateBookStock(bookId, quantity.getCartQuantity());
//			}
//		}
//
//		else {
//			throw new BookStoreException("book not available", HttpStatus.NOT_ACCEPTABLE);
//		}
//
//		return quantity;
//
//	}
//
//
//	@Override
//	@Transactional
//	public Quantity incrementQuantity(Long bookId, QuantityDto quantityDto) throws BookStoreException {
//		Book book = bookService.getBookById(bookId);
//		Quantity quantity = new Quantity();
//		BeanUtils.copyProperties(quantityDto, quantity);
//		if (book.getNoOfBooks() > 0) {
//			Long cartQuantity = quantity.getCartQuantity() + 1;
//			quantity.setCartQuantity(cartQuantity);
//			book.setQuantity(quantity);
//			quantity.setBook(book);
//			Long updatedBookQuantity = (book.getNoOfBooks() - 1);
//			book.setNoOfBooks(updatedBookQuantity);
//			quantityrepo.save(quantity);
//			bookrepo.save(book);
//
//		}
//
//		else {
//			throw new BookStoreException("book stock complete ", HttpStatus.NOT_ACCEPTABLE);
//		}
//		return quantity;
//	}
//
//	@Override
//	@Transactional
//	public Quantity decrementQuantity(Long bookId, QuantityDto quantityDto) throws BookStoreException {
//		Book book = bookService.getBookById(bookId);
//		Quantity quantity = new Quantity();
//		BeanUtils.copyProperties(quantityDto, quantity);
//
//		Long cartQuantity = quantity.getCartQuantity() - 1;
//		quantity.setCartQuantity(cartQuantity);
//		book.setQuantity(quantity);
//		quantity.setBook(book);
//		Long updatedBookQuantity = (book.getNoOfBooks() + 1);
//		book.setNoOfBooks(updatedBookQuantity);
//		quantityrepo.save(quantity);
//		bookrepo.save(book);
//
//		return quantity;
//	}

}
