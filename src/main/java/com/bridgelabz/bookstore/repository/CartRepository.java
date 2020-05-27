package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;

@Repository
public class CartRepository {
	@PersistenceContext
	private EntityManager entityManager;

	public Cart save(Cart cart) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(cart);

		return cart;

	}

	public Optional removeBook(Long bookId) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery(" delete from cart_book where booklist_book_id=:bookId").setParameter("bookId", bookId).uniqueResultOptional();

		
	}

}
