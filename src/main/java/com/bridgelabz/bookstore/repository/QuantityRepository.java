package com.bridgelabz.bookstore.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Quantity;

@Repository
public class QuantityRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Quantity save(Quantity quantity) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(quantity);

		return quantity;

	}

	public boolean updateBookStock(Long bookId,Long quantity) {
		Session session = entityManager.unwrap(Session.class);
		TypedQuery<Quantity> query = session.createQuery("update Quantity set cart_quantity=:quantity where book_id=:bookId");
		query.setParameter("quantity", quantity);
		query.setParameter("bookId", bookId);
		int status = query.executeUpdate();
		if (status > 0) {
			return true;

		} else {
			return false;
		}
	}
	

}
