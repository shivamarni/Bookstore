package com.bridgelabz.bookstore.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Seller;

@Repository
public class BookRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Book save(Book book) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(book);

		return book;

	}

}
