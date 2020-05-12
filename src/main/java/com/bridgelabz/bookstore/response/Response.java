package com.bridgelabz.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	int statusCode;
	String message;
	Object data;

}
