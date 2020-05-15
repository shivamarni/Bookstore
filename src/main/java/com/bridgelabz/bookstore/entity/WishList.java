package com.bridgelabz.bookstore.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="wishlist")
public class WishList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wishlistId;
	
	private LocalDateTime createdTime;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Book> booklist;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	
}
