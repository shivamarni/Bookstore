package com.bridgelabz.bookstore.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ForgetPassword;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.SellerDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.SellerServiceImpl;
import com.bridgelabz.bookstore.serviceimpl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(description=" Seller in Book Store")
@RequestMapping("seller")
public class SellerController {
	@Autowired
	private SellerServiceImpl sellerimpl;

	@Transactional
	@PostMapping("/register")
	@ApiOperation(value = "seller registration",response = Iterable.class)
	public ResponseEntity<Response> registerUser(@RequestBody SellerDto sellerdto)
			throws BookStoreException {
		Seller seller = sellerimpl.registerSeller(sellerdto);
		return new ResponseEntity<Response>(new Response("seller registered", seller, 200),
				HttpStatus.CREATED);

	}
	
	@Transactional
	@GetMapping("/verify/{token}")
	@ApiOperation(value = "verifying seller",response = Iterable.class)
	public ResponseEntity<Response> verifySeller(@PathVariable("token") String token) throws BookStoreException
	{
		System.out.println("inside verify");
		Seller seller=sellerimpl.verify(token);
		return new ResponseEntity<Response>(new Response("user is verified", seller, 200),HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping("/login")
	@ApiOperation(value = "seller Login",response = Iterable.class)
	public ResponseEntity<Response> loginUser(@Valid @RequestBody LoginDto logindto,BindingResult result) throws BookStoreException
	{
		String sellerToken=sellerimpl.loginSeller(logindto);
		return new ResponseEntity<Response>(new Response("login successful....", sellerToken, 200),HttpStatus.CREATED);

	}
	
	@PostMapping("/forgotPassword")
	@ApiOperation(value = "seller forget password",response = Iterable.class)
	public ResponseEntity<Response> forgotPassword(@RequestHeader String email) throws BookStoreException
	{
		Seller seller=sellerimpl.forgotPassword(email);
		return new ResponseEntity<Response>(new Response("reset password link sent to email....", seller, 200),HttpStatus.CREATED);
	}
	@PutMapping("/resetpassword/{email}")
	@ApiOperation(value = "seller reset password",response = Iterable.class)
	public ResponseEntity<Response> resetPassword(@PathVariable("email") String email,@RequestBody ForgetPassword forgotdto ) throws BookStoreException
	{
		Seller seller=sellerimpl.resetPassword(email,forgotdto);
		return new ResponseEntity<Response>(new Response("new password updated", seller, 200),HttpStatus.OK);
	
	}
	
	@GetMapping("/getallsellerbooks")
	@ApiOperation(value = "seller books",response = Iterable.class)
	public ResponseEntity<Response> getAllSellerBooks(@RequestHeader("token") String token) throws BookStoreException
	{
		System.out.println("inside method");
		List<Book> sellerBook=sellerimpl.getAllSellerBooks(token);
		return new ResponseEntity<Response>(new Response("All seller Books", sellerBook, 200),HttpStatus.OK);
	
	}
	

}
