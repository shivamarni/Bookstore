package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Seller;



@Repository
public class SellerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Seller save(Seller seller) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(seller);

		return seller;

	}
	
	public Optional<Seller> getSellerById(Long sellerId) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println(sellerId);
		return session.createQuery("FROM Seller where sellerId =:id").setParameter("id", sellerId).uniqueResultOptional();
	}
	
	public Optional<Seller> getSellerByEmail(String email) {

		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("FROM Seller where email =:email").setParameter("email", email).uniqueResultOptional();

	}

	
	
	

}
