package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.fundoonotes.entity.Note;

@Repository
public class BookRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Book save(Book book) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(book);

		return book;

	}
	
	public Optional<Book> findById(Long bookId) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Book where id=:id").setParameter("id", bookId).uniqueResultOptional();

	}

}
