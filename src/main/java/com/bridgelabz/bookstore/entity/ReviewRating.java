//
//package com.bridgelabz.bookstore.entity;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.JoinColumn;
//
//
//@Entity
//@Getter
//@Setter
//@Table(name="review_and_rating")
//public class ReviewRating {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long ratingReviewId;
//
//	@Column(name = "rating", nullable = false)
//	private Integer rating;
//	
//	@Column(name = "review", nullable = false)
//	private String review;
//	
//	@OneToOne
//	@Fetch(FetchMode.JOIN)
//	@JoinTable(name="rating_review_user", joinColumns = @JoinColumn(name="ratingReviewId"),
//	inverseJoinColumns = @JoinColumn(name="user_id"))
//	private User user;
//	
//	
//	
//		
//}
