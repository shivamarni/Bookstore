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
<<<<<<< HEAD
		//super(message);
=======
<<<<<<< HEAD
		//super(message);
=======
>>>>>>> a5012c96ac46a0e698a51f2ff515487fdac54bb6
>>>>>>> 9e79260081006563116865a2e2d652de901a8eb5
=======
>>>>>>> e5e607b26169006dad9f8f781e60e0e060d82f20
		this.message = message;
		this.status=status;
	}

	
	
}


