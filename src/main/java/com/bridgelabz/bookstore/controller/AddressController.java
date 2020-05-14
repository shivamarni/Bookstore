package com.bridgelabz.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Response> addAddress(@Valid @RequestBody AddressDto addressDto,@RequestHeader String token,BindingResult result) throws BookStoreException
	{
		if (result.hasErrors())
		return new ResponseEntity<Response>(new Response(result.getAllErrors().get(0).getDefaultMessage(),null,400),HttpStatus.BAD_REQUEST);
		Address address=addressimpl.addAddress(addressDto, token);
		return new ResponseEntity<Response>(new Response("address added", address, 200),HttpStatus.CREATED);
	}
	
	@PutMapping("address/updateAddress")
	public ResponseEntity<Response> updateAddress(@Valid @RequestBody AddressDto addressDto,@RequestHeader String token,@RequestHeader Long addressId,BindingResult result) throws BookStoreException
	{
		if (result.hasErrors())
		return new ResponseEntity<Response>(new Response(result.getAllErrors().get(0).getDefaultMessage(),null,400),HttpStatus.BAD_REQUEST);
		Address address=addressimpl.updateAddress(addressDto, token,addressId);
		return new ResponseEntity<Response>(new Response("address updated", address, 200),HttpStatus.CREATED);
	}
	@GetMapping("address/getAddressById")
	public ResponseEntity<Response> getAddressById(Long addressId,Long userId) throws BookStoreException
	{
		Address address=addressimpl.getAddressById(addressId, userId);
		return new ResponseEntity<Response>(new Response("address is",address, 200),HttpStatus.CREATED);
	}
	
	@GetMapping("address/getAllAddresses")
	public ResponseEntity<Response> getAllAddresses(String token) throws BookStoreException
	{
		List<Address> addresses=addressimpl.getAllAddresses(token);
		return new ResponseEntity<Response>(new Response("address is",addresses, 200),HttpStatus.CREATED);
	}
	
	@DeleteMapping("address/deleteAddressById/{addressId}")
	public ResponseEntity<Response> deleteAddress(String token,@PathVariable("addressId") Long addressId) throws BookStoreException
	{
		addressimpl.deleteAddress(token,addressId);
		return new ResponseEntity<Response>(new Response("address deleted",null, 200),HttpStatus.CREATED);
	}
	
	@DeleteMapping("address/deleteAllAddresses")
	public ResponseEntity<Response> deleteAllAddresses(String token) throws BookStoreException
	{
		addressimpl.deleteAllAddresses(token);
		return new ResponseEntity<Response>(new Response("all addresses deleted",null, 200),HttpStatus.CREATED);
	}
	
}
