package com.bridgelabz.bookstore.dto;

import javax.persistence.Column;

public class ReviewRatingDto {

	@Column(name = "rating", nullable = false)
	private Integer rating;
	@Column(name = "review", nullable = false)
	private String review;

	

}
