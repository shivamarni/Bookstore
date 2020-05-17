package com.bridgelabz.bookstore.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bridgelabz.bookstore.entity.Book;

public interface PaginationRepository extends PagingAndSortingRepository<Book, String> {

}
