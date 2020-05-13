package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.UserService;
import com.bridgelabz.bookstore.utility.JWTUtility;
import com.bridgelabz.bookstore.utility.JmsUtility;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userrepo;
	@Override
	public User registerUser(UserDto userdto) {
		User user=new User();
		BeanUtils.copyProperties(userdto, user);
		user.setCreatedDate(LocalDateTime.now());
		User user2=userrepo.save(user);
		JmsUtility.sendEmail(userdto.getEmail(),"verification email","http://localhost:8085/user/verify/"+JWTUtility.jwtToken(user2.getUserId()));
		return user2;
	}

	@Override
	public User loginUser(LoginDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User verify(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=getUserById(userId);
		user.setVerified(true);
		userrepo.save(user);
		return user;
	}

	@Override
	public User getUserById(Long userId) throws BookStoreException {
		return userrepo.getUserById(userId).orElseThrow(() -> new BookStoreException("no user exists!!!",HttpStatus.NOT_FOUND));
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}
	
}
