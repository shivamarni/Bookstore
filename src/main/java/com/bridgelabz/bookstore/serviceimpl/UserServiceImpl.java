package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.User;
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
	public User verify(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}
	
}
