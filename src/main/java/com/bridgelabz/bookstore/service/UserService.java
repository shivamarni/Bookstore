package com.bridgelabz.bookstore.service;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.User;

public interface UserService {
	
	User registerUser(UserDto dto);
	User loginUser(LoginDto dto);
	User verify(String token);
	User getUserById(Long userId);
	void deleteUser(Long userId);

}
