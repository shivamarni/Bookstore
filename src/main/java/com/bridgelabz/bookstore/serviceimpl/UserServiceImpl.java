package com.bridgelabz.bookstore.serviceimpl;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Override
	public User registerUser(UserDto dto) {
		// TODO Auto-generated method stub
		return null;
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
