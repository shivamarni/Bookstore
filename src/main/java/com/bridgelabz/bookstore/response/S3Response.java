package com.bridgelabz.bookstore.response;


import java.util.HashMap;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class S3Response {

	public S3Response() {
		// TODO Auto-generated constructor stub
	}
	String message;
	int statusCode;
	HashMap<String, String> data;
	public S3Response(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	public S3Response(String message, int statusCode, HashMap<String, String> data) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.data = data;
	}
	
	
	

}
