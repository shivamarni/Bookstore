package com.bridgelabz.bookstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="book")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,scope = Book.class)

public class Book implements Serializable {
	
	
	private static final long serialVersionUID = 8441630761490458497L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;
	
	@Column(name = "book_name", nullable = false)
	private String bookName;
	
	@Column(name = "book_author", nullable = false)
	private String bookAuthor;
	
	@Column(name = "book_price", nullable = false)
	private Double bookPrice;
	
	@Column(name = "no_of_books", nullable = false)
	private Long noOfBooks;
	
	@Column(name = "book_image")
	private String bookImage;
	
	@Column(name = "book_description", nullable = false)
	private String bookDescription;
	
	@Column(name = "book_verified")
	private boolean isBookVerified;
	
	@Column(name = "book_added_time", nullable = false)
	private LocalDateTime bookAddedTime;	

	@Column(name = "book_updated_time", nullable = false)
	private LocalDateTime bookUpdatedTime;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "book_id")
//	private List<ReviewRating> reviewRating;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "booklist")
	private List<Cart> cartlist;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "book",optional = false)
	private Quantity quantity;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Order.class)
	private List<Order> order;
	

}
