package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.ForgetPassword;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.entity.WishList;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.repository.WishListRepository;
import com.bridgelabz.bookstore.service.UserService;
import com.bridgelabz.bookstore.utility.JWTUtility;
import com.bridgelabz.bookstore.utility.JmsUtility;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private BCryptPasswordEncoder pwdBcrypt;
	@Autowired
	private CartRepository cartrepo;
	@Autowired
	private WishListRepository wishrepo;
	@Override
	public User registerUser(UserDto userdto) throws BookStoreException {
		if(userrepo.getUserByEmail(userdto.getEmail()).isPresent()==false) {
		User user=new User();
		BeanUtils.copyProperties(userdto, user);
		user.setPassword(pwdBcrypt.encode(userdto.getPassword()));
		user.setCreatedDate(LocalDateTime.now());
		user.setUpdatedDate(LocalDateTime.now());
		User user2=userrepo.save(user);
		Cart cart=new Cart();
		cart.setCreatedTime(LocalDateTime.now());
		user.setCart(cart);
		cart.setUser(user);
		cartrepo.save(cart);
		WishList wishlist=new WishList();
		wishlist.setCreatedTime(LocalDateTime.now());
		user.setWishlist(wishlist);
		wishlist.setUser(user);
		cartrepo.save(cart);
		wishrepo.save(wishlist);
		userrepo.save(user);
		JmsUtility.sendEmail(userdto.getEmail(),"verification email","http://localhost:3000/verify/"+JWTUtility.jwtToken(user2.getUserId()));
		return user2;
		}
		else
			throw new BookStoreException("Email already exists",HttpStatus.BAD_REQUEST);
	}

	@Override
	public String loginUser(LoginDto dto) throws BookStoreException {
		User user=getUserByEmail(dto.getEmail());
		boolean ispwd=pwdBcrypt.matches(dto.getPassword(),user.getPassword());
		if(ispwd==false) {
			throw new BookStoreException("incorrect password", HttpStatus.BAD_REQUEST);
		}
		return JWTUtility.jwtToken(user.getUserId());
	}

	@Override
	public User verify(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=getUserById(userId);
		user.setVerified(true);
		userrepo.save(user);
		return user;
	}
	public User getUserByEmail(String email) throws BookStoreException
	{
		User user=userrepo.getUserByEmail(email).orElseThrow(() -> new BookStoreException("no user exists", HttpStatus.NOT_FOUND));
		return user;
	}
	public boolean isEmailExists(String email) throws BookStoreException {
		if(userrepo.isEmailExists(email).isPresent())
			throw new BookStoreException("email already exists", HttpStatus.BAD_REQUEST);
		return false;
	}

	@Override
	public User getUserById(Long userId) throws BookStoreException {
		return userrepo.getUserById(userId).orElseThrow(() -> new BookStoreException("no user exists!!!",HttpStatus.NOT_FOUND));
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

	public User forgotPassword(String email) throws BookStoreException {
		User user=getUserByEmail(email);
		String token=JWTUtility.jwtToken(user.getUserId());
		JmsUtility.sendEmail(email, "reset your password", "http://localhost:3000/user/resetpassword/"+token);
		return null;
	}

	public User resetPassword(String email, ForgetPassword forgotDto) throws BookStoreException {
		User user=getUserByEmail(email);
		user.setPassword(pwdBcrypt.encode(forgotDto.getPassword()));
		userrepo.save(user);
		return user;
	}
	
}
