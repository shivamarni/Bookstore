package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Admin;
@Repository
public class AdminRepository {


	@PersistenceContext
	private EntityManager entityManager;

	public Admin save(Admin admin) {

		Session session = entityManager.unwrap(Session.class);

		session.saveOrUpdate(admin);

		return admin;

	}
	
	public Optional<Admin> getAdminById(Long adminId) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println(adminId);
		return session.createQuery("FROM Admin where adminId =:id").setParameter("id", adminId).uniqueResultOptional();
	}
	
	public Optional<Admin> getAdminByEmail(String email) {

		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("FROM Admin where email =:email").setParameter("email", email).uniqueResultOptional();

	}

}
