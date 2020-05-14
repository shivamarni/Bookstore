package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.OrderedBooks;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.CartRepository;
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
	private UserRepository userrepo;
	@Override
	@Transactional
	public List<Book> addBookToCart(Long bookId, String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Book book=bookImpl.getBookById(bookId);
//		Employee employee = new Employee();
//		employee.setFirstName("Julez");
//		employee.setLastName("Jupiter");
//
//		Security security = new Security();
//		security.setUsername("julez");
//		security.setPassword("pwd");
//
//		employee.setSecurity(security);
//		security.setEmployee(employee);
//
//		employeeRepository.save(employee);
		Cart cart=new Cart();
		cart.setCreatedTime(LocalDateTime.now());
		user.setCart(cart);
		cart.setUser(user);
		List<Book> books=cart.getBooklist();
		if(books==null)
		{
			ArrayList<Book> booklist = new ArrayList<>();
			booklist.add(book);
		}
		userrepo.save(user);
		return null;
	}
	
	//public boolean isBookExistsInCart(Long bookId,Long )
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
