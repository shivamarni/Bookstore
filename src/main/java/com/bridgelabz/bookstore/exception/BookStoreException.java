package com.bridgelabz.bookstore.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BookStoreException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	HttpStatus status;

	public BookStoreException(String message,HttpStatus status) {
<<<<<<< HEAD
		//super(message);
=======
>>>>>>> a5012c96ac46a0e698a51f2ff515487fdac54bb6
		this.message = message;
		this.status=status;
	}

	
	
}


