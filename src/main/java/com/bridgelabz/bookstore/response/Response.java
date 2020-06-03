package com.bridgelabz.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	String message;
	Object data;
	int statusCode;
}
