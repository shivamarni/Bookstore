package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.SellerDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface SellerService {
    Seller registerSeller(SellerDto sellerdto)throws BookStoreException;
	Seller loginSeller(LoginDto dto) throws BookStoreException;
	Seller verify(String token) throws BookStoreException;
	Seller getSellerById(Long sellerId) throws BookStoreException;
	void deleteSeller(Long userId);
	public List<Seller> getSellers();

	
}
