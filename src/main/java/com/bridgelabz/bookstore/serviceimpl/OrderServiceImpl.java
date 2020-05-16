package com.bridgelabz.bookstore.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.utility.JWTUtility;

@Service
public class OrderServiceImpl {

	@Autowired
	private CartServiceImpl cartImpl;
	@Autowired
	private UserServiceImpl userImpl;
	public List<Book> getAllOrders(String token) throws BookStoreException {
		System.out.println("--------------------------------------------------------------");
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		System.out.println(user.getCart());
		return user.getCart().getBooklist();
	}
	
	

}
