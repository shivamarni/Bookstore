package com.bridgelabz.bookstore.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
	private String bookName;

	private String bookAuthor;

	private Double bookPrice;

	private Long noOfBooks;

	private String bookDescription;

}
