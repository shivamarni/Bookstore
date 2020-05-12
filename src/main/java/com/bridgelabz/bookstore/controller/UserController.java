package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceImpl userimpl;
	
	@PostMapping("user/registerUser/")
	public ResponseEntity<Response> registerUser(@RequestBody UserDto userdto,BindingResult result)
	{
		if(result.hasErrors())
		return new ResponseEntity<Response>(new Response("invalid details",null,400),HttpStatus.BAD_REQUEST);
		User user=userimpl.registerUser(userdto);
		return new ResponseEntity<Response>(new Response("user registered and verification mail sent", user, 200),HttpStatus.CREATED);

	}

}
