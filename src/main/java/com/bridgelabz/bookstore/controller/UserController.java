package com.bridgelabz.bookstore.controller;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.ForgetPassword;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.UserServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Api(description=" User in Book Store")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userimpl;
	@PostMapping("/register")
	@ApiOperation(value = "user registration",response = Iterable.class)
	public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userdto,BindingResult result) throws BookStoreException
	{
		if (result.hasErrors())
		return new ResponseEntity<Response>(new Response(result.getAllErrors().get(0).getDefaultMessage(),null,400),HttpStatus.BAD_REQUEST);
		User user=userimpl.registerUser(userdto);
		return new ResponseEntity<Response>(new Response("user registered and verification mail sent", user, 200),HttpStatus.CREATED);

	}
	
	@PostMapping("/login")
	@ApiOperation(value = "user Login",response = Iterable.class)
	public ResponseEntity<Response> loginUser(@Valid @RequestBody LoginDto logindto,BindingResult result) throws BookStoreException
	{
		if (result.hasErrors())
		return new ResponseEntity<Response>(new Response(result.getAllErrors().get(0).getDefaultMessage(),null,400),HttpStatus.BAD_REQUEST);
		String token=userimpl.loginUser(logindto);
		return new ResponseEntity<Response>(new Response("login successful....", token, 200),HttpStatus.CREATED);

	}
	
	@PostMapping("/forgotpassword")
	@ApiOperation(value = "user forget password",response = Iterable.class)
	public ResponseEntity<Response> forgotPassword(@RequestHeader String email) throws BookStoreException
	{
		User user=userimpl.forgotPassword(email);
		return new ResponseEntity<Response>(new Response("reset password link sent to email....", user, 200),HttpStatus.CREATED);
	}
	@PutMapping("/verify/{token}")
	@ApiOperation(value = "verifying user",response = Iterable.class)
	public ResponseEntity<Response> verifyuser(@PathVariable("token") String token) throws BookStoreException
	{
		User user=userimpl.verify(token);
		return new ResponseEntity<Response>(new Response("user is verified", user, 200),HttpStatus.OK);
	}
	@PutMapping("/resetpassword/{email}")
	@ApiOperation(value = "user reset password",response = Iterable.class)
	public ResponseEntity<Response> resetPassword(@PathVariable("email") String email,@RequestBody ForgetPassword forgotdto ) throws BookStoreException
	{
		User user=userimpl.resetPassword(email,forgotdto);
		return new ResponseEntity<Response>(new Response("new password updated", user, 200),HttpStatus.OK);
	}
	
	

}