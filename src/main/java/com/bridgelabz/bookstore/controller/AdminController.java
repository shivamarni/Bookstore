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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ForgetPassword;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Admin;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.AdminServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(description=" Admin controller for bookstore")
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private AdminServiceImpl adminimpl;
	@Transactional
	@PostMapping("/register")
	@ApiOperation(value = "Admin registration",response = Iterable.class)
	public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userdto, BindingResult result)
			throws BookStoreException {
		Admin admin = adminimpl.registerAdmin(userdto);
		return new ResponseEntity<Response>(new Response("admin"
				+ " registered", admin, 200),
				HttpStatus.CREATED);

	}
	@Transactional
	@PostMapping("/login")
	@ApiOperation(value = "Admin Login",response = Iterable.class)
	public ResponseEntity<Response> loginUser(@Valid @RequestBody LoginDto logindto,BindingResult result) throws BookStoreException
	{
		Admin admin=adminimpl.loginAdmin(logindto);
		return new ResponseEntity<Response>(new Response("login successful....", admin, 200),HttpStatus.CREATED);

	}
	@Transactional
	@GetMapping("/verify/{token}")
	@ApiOperation(value = "Admin verify",response = Iterable.class)
	public ResponseEntity<Response> verifyAdmin(@PathVariable("token") String token) throws BookStoreException
	{
		System.out.println("inside verify");
		Admin admin=adminimpl.verify(token);
		return new ResponseEntity<Response>(new Response("user is verified", admin, 200),HttpStatus.OK);
	}
	@PostMapping("/forgotpassword")
	@ApiOperation(value = "Admin forgot password",response = Iterable.class)
	public ResponseEntity<Response> forgotPassword(@RequestHeader String email) throws BookStoreException
	{
		Admin admin=adminimpl.forgotPassword(email);
		return new ResponseEntity<Response>(new Response("reset password link sent to email....", admin, 200),HttpStatus.CREATED);
	}
	@PutMapping("/resetpassword/{email}")
	@ApiOperation(value = "Admin ResetPassword",response = Iterable.class)
	public ResponseEntity<Response> resetPassword(@PathVariable("email") String email,@RequestBody ForgetPassword forgotdto ) throws BookStoreException
	{
		Admin admin=adminimpl.resetPassword(email,forgotdto);
		return new ResponseEntity<Response>(new Response("new password updated", admin, 200),HttpStatus.OK);
	
	}
}
