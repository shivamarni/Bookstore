package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.JWTUtility;
import com.bridgelabz.bookstore.utility.JmsUtility;

@Service
public class OrderServiceImpl {

	@Autowired
	private CartServiceImpl cartImpl;
	@Autowired
	private UserServiceImpl userImpl;
	@Autowired
	private AddressServiceImpl addressImpl;
	@Autowired
	private OrderRepository orderrepo;
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private CartRepository cartrepo;
	@Autowired
	private QuantityRepository quantrepo;
	public Order addOrder(String token, Long totalCartPrice, Long deliveryCharges, String type) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Address address=addressImpl.getAddressForUser(token, type);
		Cart cart=user.getCart();
		List<Book> books=cart.getBooklist();
		Order order=new Order();
		order.setDeliveryCharges(deliveryCharges);
		order.setTotalCartPrice(totalCartPrice);
		order.setOrderedTime(LocalDateTime.now());
		//order.setAddress(address);
		//order.setOrderedBooks(books);
		user.getOrderedBooks().add(order);
		user.getCart().getBooklist().clear();
		quantrepo.deleteAll();
		userrepo.save(user);
		JmsUtility.sendEmail(user.getEmail(),"ordered details",order);
		return order;
	}
	
	

}
