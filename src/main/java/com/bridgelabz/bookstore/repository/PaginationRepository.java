package com.bridgelabz.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bridgelabz.bookstore.entity.Book;

public interface PaginationRepository extends PagingAndSortingRepository<Book, String> {
	
	
	@Query(value = "select * from book where seller_id=?2",nativeQuery = true)
	Page<Book> findAll(Pageable paging, Long sellerId);

}
