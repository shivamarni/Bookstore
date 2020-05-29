package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface AddressService {
	
	Address updateAddress(AddressDto addressDto,String token,Long addressId) throws BookStoreException;
	void deleteAddress(String token,Long addressId) throws BookStoreException;
	List<Address> getAllAddresses(String token) throws BookStoreException;
	Address getAddressById(Long addressId, Long userId) throws BookStoreException;
	Address addAddress(AddressDto addressDto, String token, String type) throws BookStoreException;
}
