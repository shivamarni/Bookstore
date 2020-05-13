package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder pwdBcrypt;
	@Override
	public User registerUser(UserDto userdto) throws BookStoreException {
		isEmailExists(userdto.getEmail());
		User user=new User();
		BeanUtils.copyProperties(userdto, user);
		user.setPassword(pwdBcrypt.encode(userdto.getPassword()));
		user.setCreatedDate(LocalDateTime.now());
		User user2=userrepo.save(user);
		JmsUtility.sendEmail(userdto.getEmail(),"verification email","http://localhost:8085/user/verify/"+JWTUtility.jwtToken(user2.getUserId()));
		return user2;
	}

	@Override
	public User loginUser(LoginDto dto) throws BookStoreException {
		User user=userrepo.getUserByEmail(dto.getEmail()).orElseThrow(() -> new BookStoreException("no user exists", HttpStatus.NOT_FOUND));
		boolean ispwd=pwdBcrypt.matches(dto.getPassword(),user.getPassword());
		if(ispwd==false) {
			throw new BookStoreException("incorrect password", HttpStatus.BAD_REQUEST);
		}
		return user;
	}

	@Override
	public User verify(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=getUserById(userId);
		user.setVerified(true);
		userrepo.save(user);
		return user;
	}
	
	public boolean isEmailExists(String email) throws BookStoreException {
		if(userrepo.isEmailExists(email).isPresent())
			throw new BookStoreException("email not exists", HttpStatus.BAD_REQUEST);
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
	
}
