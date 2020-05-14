package com.bridgelabz.bookstore.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
	@NotNull(message = "book name required for add book")
	private String bookName;
	
	@NotNull(message = "author name required for add book")
	private String bookAuthor;
	
	@NotNull(message = "book price required for add book")
	private Double bookPrice;

	@NotNull(message = "book quantity required for add book")
	private Long noOfBooks;

	private String bookDescription;

}
