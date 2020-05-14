package com.bridgelabz.bookstore.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ForgetPassword;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.SellerServiceImpl;
import com.bridgelabz.bookstore.serviceimpl.UserServiceImpl;

@RestController
public class SellerController {
	@Autowired
	private SellerServiceImpl sellerimpl;

	@Transactional
	@PostMapping("seller/register/")
	public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userdto, BindingResult result)
			throws BookStoreException {
		Seller seller = sellerimpl.registerSeller(userdto);
		return new ResponseEntity<Response>(new Response("seller registered", seller, 200),
				HttpStatus.CREATED);

	}
	
	@Transactional
	@GetMapping("seller/verify/{token}")
	public ResponseEntity<Response> verifySeller(@PathVariable("token") String token) throws BookStoreException
	{
		System.out.println("inside verify");
		Seller seller=sellerimpl.verify(token);
		return new ResponseEntity<Response>(new Response("user is verified", seller, 200),HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("seller/login")
	public ResponseEntity<Response> loginUser(@Valid @RequestBody LoginDto logindto,BindingResult result) throws BookStoreException
	{
		Seller seller=sellerimpl.loginSeller(logindto);
		return new ResponseEntity<Response>(new Response("login successful....", seller, 200),HttpStatus.CREATED);

	}
	
	@PostMapping("seller/forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestHeader String email) throws BookStoreException
	{
		Seller seller=sellerimpl.forgotPassword(email);
		return new ResponseEntity<Response>(new Response("reset password link sent to email....", seller, 200),HttpStatus.CREATED);
	}
	@PutMapping("seller/resetPassword/{email}")
	public ResponseEntity<Response> resetPassword(@PathVariable("email") String email,@RequestBody ForgetPassword forgotdto ) throws BookStoreException
	{
		Seller seller=sellerimpl.resetPassword(email,forgotdto);
		return new ResponseEntity<Response>(new Response("new password updated", seller, 200),HttpStatus.OK);
	
	}
	

}
