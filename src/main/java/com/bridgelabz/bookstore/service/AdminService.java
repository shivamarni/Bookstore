package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.ForgetPassword;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Admin;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface AdminService {
	
	Admin registerAdmin(UserDto userdto)throws BookStoreException;
	Admin loginAdmin(LoginDto dto) throws BookStoreException;
	Admin verify(String token) throws BookStoreException;
    Admin forgotPassword(String email) throws BookStoreException;
    Admin resetPassword(String email, ForgetPassword forgotDto) throws BookStoreException;
    Admin getAdminById(Long adminId) throws BookStoreException;
}
