package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.AdminDto;
import com.bridgelabz.bookstore.dto.AdminResetPasswordDto;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.entity.Admin;

public interface AdminService {
	Admin login(LoginDto adminLoginDto);

	boolean sendLinkForPassword(String email);

	boolean resetAdminPassword(AdminResetPasswordDto resetDto);

	boolean verifyEmail(String token);

	boolean register(AdminDto adminDto);

	boolean verifyBook(String booktoken,String token);
}
