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
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.entity.WishList;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.repository.WishListRepository;
import com.bridgelabz.bookstore.service.CartService;
import com.bridgelabz.bookstore.service.WishListService;
import com.bridgelabz.bookstore.utility.JWTUtility;

@Service
public class WishListServiceImpl implements WishListService{

	@Autowired
	private BookServiceImpl bookImpl;
	@Autowired
	private UserServiceImpl userImpl;
	@Autowired
	private WishListRepository wishrepo;
	@Autowired
	private UserRepository userrepo;
	@Override
	public List<Book> addBookToWishList(Long bookId, String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Book book=bookImpl.getBookById(bookId);
		WishList wishlist=new WishList();
		if(user.getCart()==null)
		{
		wishlist.setCreatedTime(LocalDateTime.now());
		user.setWishlist(wishlist);
		wishlist.setUser(user);
		}
		wishlist=user.getWishlist();
		Optional<Book> isBook =wishlist.getBooklist().stream().filter(isBookExists -> isBookExists.getBookId() == bookId).findFirst();
		if(isBook.isPresent())
			throw new BookStoreException("Book already exists",HttpStatus.BAD_REQUEST);
		wishlist.getBooklist().add(book);
		return wishlist.getBooklist();
	}
	@Override
	public List<Book> deleteBookFromWishList(Long bookId, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteAllBooksFromWishList(String token) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Book updateBookQuantityInWishList(Long bookId, String token) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Book getWishListBook(Long bookId, String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Book book=bookImpl.getBookById(bookId);
		WishList wishlist=user.getWishlist();
		Book book2=wishlist.getBooklist().stream().filter(book1 -> book1.getBookId()==bookId).findFirst().orElseThrow(()-> new BookStoreException("no book in the cart",HttpStatus.NOT_FOUND));
		return book2;
	}
	@Override
	public List<Book> getAllWishListBooks(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		WishList wishlist=user.getWishlist();
		return wishlist.getBooklist();
	}
	@Override
	public void deleteAllWishListBooks(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		WishList wishlist=user.getWishlist();
		wishlist.setBooklist(null);
		wishrepo.save(wishlist);
		
	}
	@Override
	public List<Book> deleteWishListBook(Long bookId, String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Book book=bookImpl.getBookById(bookId);
		WishList wishlist=user.getWishlist();
		Book book2=wishlist.getBooklist().stream().filter(book1 -> book1.getBookId()==bookId).findFirst().orElseThrow(()-> new BookStoreException("no book in the cart",HttpStatus.NOT_FOUND));
		wishlist.getBooklist().remove(book2);
		wishrepo.save(wishlist);
		return wishlist.getBooklist();
	}
}
