package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.QuantityDto;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.exception.BookStoreException;

public interface QuantityService {

	Quantity mapQuantityToBook(Long bookId, QuantityDto quantityDto) throws BookStoreException;

	Quantity incrementQuantity(Long bookId, QuantityDto quantityDto) throws BookStoreException;

//	Quantity decrementQuantity(Long bookId) throws BookStoreException;

	Quantity decrementQuantity(Long bookId, QuantityDto quantityDto) throws BookStoreException;

}
