package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.OrderedBooks;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.CartService;
import com.bridgelabz.bookstore.utility.JWTUtility;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private BookServiceImpl bookImpl;
	@Autowired
	private UserServiceImpl userImpl;
	@Autowired
	private CartRepository cartrepo;
	@Autowired
	private CartServiceImpl cartservice;
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private BookRepository bookrepo;
	@Autowired
	private QuantityRepository quantrepo;
	@Override
	@Transactional
	public List<Book> addBookToCart(Long bookId, String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Book book=bookImpl.getBookById(bookId);
		Quantity quantity=new Quantity();
		long quant=1;
		quantity.setCartQuantity(quant);
		Cart cart=new Cart();
		if(user.getCart()==null)
		{
		cart.setCreatedTime(LocalDateTime.now());
		user.setCart(cart);
		cart.setUser(user);
		userrepo.save(user);
		}
		cart=user.getCart();
		System.out.println(cart);
		Optional<Book> isBook =cart.getBooklist().stream().filter(isBookExists -> isBookExists.getBookId() == bookId).findFirst();
		if(isBook.isPresent())
			throw new BookStoreException("Book already exists",HttpStatus.BAD_REQUEST);
		quantity.setBook(book);
		quantrepo.save(quantity);
		book.setQuantity(quantity);
		bookrepo.save(book);
		cart.getBooklist().add(book);
		return cart.getBooklist();


	}

	@Override
	public Book updateBookQuantityInCart(Long bookId, String token) {
		return null;
	}
	@Override
	public List<Book> getAllCartBooks(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Cart cart=user.getCart();
		return cart.getBooklist();
	}
	@Override
	public Book getCartBook(Long bookId, String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Book book=bookImpl.getBookById(bookId);
		Cart cart=user.getCart();
		Book book2=cart.getBooklist().stream().filter(book1 -> book1.getBookId()==bookId).findFirst().orElseThrow(()-> new BookStoreException("no book in the cart",HttpStatus.NOT_FOUND));
		return book2;
	}
	@Override
	public void deleteAllCartBooks(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Cart cart=user.getCart();
		cart.setBooklist(null);
		cartrepo.save(cart);
	}
	
	@Override
	public List<Book> deleteCartBook(Long bookId, String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Book book=bookImpl.getBookById(bookId);
		Cart cart=user.getCart();
		Book book2=cart.getBooklist().stream().filter(book1 -> book1.getBookId()==bookId).findFirst().orElseThrow(()-> new BookStoreException("no book in the cart",HttpStatus.NOT_FOUND));
		cart.getBooklist().remove(book2);
		cartrepo.save(cart);
		return cart.getBooklist();
	}
	

}
