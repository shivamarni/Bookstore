package com.bridgelabz.bookstore.service;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface UserService {
	
	User registerUser(UserDto dto) throws BookStoreException;
	String loginUser(LoginDto dto) throws BookStoreException;
	User verify(String token) throws BookStoreException;
	User getUserById(Long userId) throws BookStoreException;
	void deleteUser(Long userId);

}
