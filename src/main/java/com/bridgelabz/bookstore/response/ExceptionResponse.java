package com.bridgelabz.bookstore.response;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ExceptionResponse {
	String message;
	HttpStatus code;
	LocalDateTime time;
	
	
	public ExceptionResponse() {
		// TODO Auto-generated constructor stub
	}
	


	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public HttpStatus getCode() {
		return code;
	}



	public void setCode(HttpStatus code) {
		this.code = code;
	}



	public LocalDateTime getTime() {
		return time;
	}



	public void setTime(LocalDateTime time) {
		this.time = time;
	}



	public ExceptionResponse(String message, HttpStatus code, LocalDateTime time) {
		//super();
		this.message = message;
		this.code = code;
		this.time = time;
	}
	
	
	

}
