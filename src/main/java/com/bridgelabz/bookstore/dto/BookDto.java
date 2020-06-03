package com.bridgelabz.bookstore.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@AllArgsConstructor
@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = BookDto.BookDtoBuilder.class)
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
	
//	private MultipartFile logo;
}
