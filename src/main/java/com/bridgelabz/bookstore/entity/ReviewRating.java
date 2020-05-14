
package com.bridgelabz.bookstore.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name="review_and_rating")
public class ReviewRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratingReviewId;

	@Column(name = "rating", nullable = false)
	private Integer rating;
	
	@Column(name = "review", nullable = false)
	private String review;
	
	@OneToOne
	@JoinTable(name="rating_review_user", joinColumns = @JoinColumn(name="ratingReviewId"),
	inverseJoinColumns = @JoinColumn(name="user_id"))
	private User user;
	
	
	
		
}
