package com.bridgelabz.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Data;
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
}
