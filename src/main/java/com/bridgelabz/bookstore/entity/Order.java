package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
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

@Entity
@Table(name="orderedbooks")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private LocalDateTime orderedTime;
	
	private Long deliveryCharges;
	
	private Long totalCartPrice;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name = "address_id", nullable = false)
	private Address address;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "order")
	private List<Book> orderedBooks;
	
	
	
	
	
	
}
