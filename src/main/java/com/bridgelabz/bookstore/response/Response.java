package com.bridgelabz.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Data;
<<<<<<< HEAD
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	int statusCode;
	String message;
	Object data;

=======
@Data
@AllArgsConstructor
public class Response {
	private int statusCode;
	private String message;
	private Object data;
	
public Response(String message, Object data, int statuscode) {
		this.message=message;
		this.data=data;
		this.statusCode=statuscode;
	}
>>>>>>> 44d5200f569d62532349c50f288e84c689fa5f93
}
