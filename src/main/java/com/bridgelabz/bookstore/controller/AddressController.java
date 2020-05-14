package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.serviceimpl.AddressServiceImpl;

@RestController
public class AddressController {
	
	@Autowired
	private AddressServiceImpl addressimpl;
	@PostMapping("address/addAddress")
	public ResponseEntity<Response> addAddress(@RequestBody AddressDto addressDto,@RequestHeader String token) throws BookStoreException
	{
		Address address=addressimpl.addAddress(addressDto, token);
		return new ResponseEntity<Response>(new Response("address added", address, 200),HttpStatus.CREATED);
	}
}
