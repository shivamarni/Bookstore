package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

	public boolean removeBookQuantity(Long bookId) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("DELETE FROM Quantity where book_id=:id").setParameter("id", bookId);
		int result = query.executeUpdate();
		if (result >= 1) {
			return true;

		}
		return false;

	}

	public boolean deleteCartBookList(Long cartId) {
Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("DELETE FROM cart_booklist where cartlist_cart_id=:cartId").setParameter("cartId", cartId);
		int result = query.executeUpdate();
		if (result >= 1) {
			return true;

		}
		return false;

	}


}
