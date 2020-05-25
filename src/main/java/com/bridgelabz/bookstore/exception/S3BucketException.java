package com.bridgelabz.bookstore.exception;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class S3BucketException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	HttpStatus status;

	public S3BucketException(String message,HttpStatus status) {
		//super(message);
		this.message = message;
		this.status=status;
	}
}


