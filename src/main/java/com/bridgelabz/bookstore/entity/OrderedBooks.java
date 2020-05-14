package com.bridgelabz.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderedbooks")
public class OrderedBooks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
}
