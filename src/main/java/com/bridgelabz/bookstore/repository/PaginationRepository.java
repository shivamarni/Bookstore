package com.bridgelabz.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Book;

public interface PaginationRepository extends JpaRepository<Book, String> {

@Query(value = "SELECT * FROM book WHERE seller_id = ?1 ORDER BY ?#{#pageable}",
//       countQuery = "SELECT count(*) FROM book WHERE seller_id = ?1",
       nativeQuery = true)
   Page<Book> findAll(Long sellerId, Pageable pageable);   
}