package com.bridgelabz.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
//import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Quantity;
@Repository
public interface QuantityRepository extends JpaRepository<Quantity,String>
{
	@Query(value="select book_id FROM quantity WHERE book_id = ?1",nativeQuery = true)
	Long isBookQuantMapped(Long bookId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE quantity SET cart_quantity=?1 WHERE book_id = ?2",nativeQuery = true)
	void alterQuantity(Long cartQuantity,Long bookId);
}
//public class QuantityRepository {
	
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	public Quantity save(Quantity quantity) {
//
//		Session session = entityManager.unwrap(Session.class);
//
//		session.saveOrUpdate(quantity);
//
//		return quantity;
//
//	}
//
//	public boolean updateBookStock(Long bookId,Long quantity) {
//		Session session = entityManager.unwrap(Session.class);
//		TypedQuery<Quantity> query = session.createQuery("update Quantity set cart_quantity=:quantity where book_id=:bookId");
//		query.setParameter("quantity", quantity);
//		query.setParameter("bookId", bookId);
//		int status = query.executeUpdate();
//		if (status > 0) {
//			return true;
//
//		} else {
//			return false;
//		}
//	}
//	
//	public List<Quantity> getAllQuantity() {
//		Session session = entityManager.unwrap(Session.class);
//		Query query=session.createQuery("  from Quantity");
//		List<Quantity> quantityList=query.list();
//		return quantityList;
//		
//
//
//		
//	}
