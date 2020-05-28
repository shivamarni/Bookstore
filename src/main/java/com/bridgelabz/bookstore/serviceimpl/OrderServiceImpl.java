package com.bridgelabz.bookstore.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.JWTUtility;

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
	public Order getAllOrders(String token, Long totalCartPrice, Long deliveryCharges, String type) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userImpl.getUserById(userId);
		Address address=addressImpl.getAddressForUser(token, type);
		Order order=new Order();
		order.setDeliveryCharges(deliveryCharges);
		order.setTotalCartPrice(totalCartPrice);
		order.setType(type);
		user.getOrderedBooks().add(order);
		userrepo.save(user);
		//orderrepo.save(order);
		System.out.println(user.getCart());
		return order;
	}
	
	

}
