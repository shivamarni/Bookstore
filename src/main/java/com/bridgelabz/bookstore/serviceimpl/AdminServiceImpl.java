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
import com.bridgelabz.bookstore.entity.Admin;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.AdminRepository;
import com.bridgelabz.bookstore.repository.SellerRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.AdminService;
import com.bridgelabz.bookstore.utility.JWTUtility;
import com.bridgelabz.bookstore.utility.JmsUtility;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminrepo;
	@Autowired
	private BCryptPasswordEncoder pwdBcrypt;
	@Override
	public Admin registerAdmin(UserDto userdto) throws BookStoreException {
		
		if(adminrepo.getAdminByEmail(userdto.getEmail()).isPresent()==false)
		{
		Admin admin=new Admin();
		BeanUtils.copyProperties(userdto, admin);
		admin.setPassword(pwdBcrypt.encode(userdto.getPassword()));
		admin.setCreatedTime(LocalDateTime.now());
		admin.setUpdatedTime(LocalDateTime.now());
		adminrepo.save(admin);
		JmsUtility.sendEmail(userdto.getEmail(),"verification email","http://localhost:8085/seller/verify/"+JWTUtility.jwtToken(admin.getAdminId()));
		return admin;
		}
		else {
			
			throw new BookStoreException("email already registered",HttpStatus.BAD_REQUEST);
		}
		
	
	}
	@Override
	public Admin loginAdmin(LoginDto dto) throws BookStoreException {
		Admin admin=getAdminByEmail(dto.getEmail());
		boolean ispwd=pwdBcrypt.matches(dto.getPassword(),admin.getPassword());
		if(ispwd==false) {
			throw new BookStoreException("incorrect password", HttpStatus.BAD_REQUEST);
		}
		return admin;
	}
	
	@Override
	public Admin verify(String token) throws BookStoreException {
	Long adminId=JWTUtility.parseJWT(token);
		
		Admin admin=getAdminById(adminId);
		System.out.println(admin);
		admin.isVerified();
		admin.setVerified(true);
		adminrepo.save(admin);
		return admin;
	}
	@Override
	public Admin forgotPassword(String email) throws BookStoreException {
		getAdminByEmail(email);
		JmsUtility.sendEmail(email, "reset your password", "http://localhost:8085/user/resetPassword/"+email);
		return null;
	}
	@Override
	public Admin resetPassword(String email, ForgetPassword forgotDto) throws BookStoreException {
		Admin admin=getAdminByEmail(email);
		admin.setPassword(pwdBcrypt.encode(forgotDto.getPassword()));
		adminrepo.save(admin);
		return admin;
	}
	public Admin getAdminByEmail(String email) throws BookStoreException
	{
		Admin admin=adminrepo.getAdminByEmail(email).orElseThrow(() -> new BookStoreException("no user exists", HttpStatus.NOT_FOUND));
		return admin;
	}
	@Override
	public Admin getAdminById(Long adminId) throws BookStoreException {
		return adminrepo.getAdminById(adminId).orElseThrow(() -> new BookStoreException("no Admin exists!!!",HttpStatus.NOT_FOUND));
	}
}
