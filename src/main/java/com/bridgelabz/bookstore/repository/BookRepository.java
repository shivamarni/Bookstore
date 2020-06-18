package com.bridgelabz.bookstore.repository;

import java.util.List;
import java.util.Optional;

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

	public Optional<Book> findById(Long bookId) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from Book where id=:id").setParameter("id", bookId).uniqueResultOptional();

	}

	public List<Book> getAllBooks() {
		Session session = entityManager.unwrap(Session.class);

		return session.createQuery("from Book").list();
	}

	public Optional<Book> verifyBook(Long bookId) {
		
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("Update Book set book_verified=:verify where book_id =:id").setParameter("verify", true).setParameter("id", bookId).uniqueResultOptional();

	}

	

}
