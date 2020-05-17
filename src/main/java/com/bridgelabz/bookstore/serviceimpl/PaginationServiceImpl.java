package com.bridgelabz.bookstore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.repository.PaginationRepository;

@Service
public class PaginationServiceImpl {
	
	
	@Autowired
	private PaginationRepository pagerepo;
	@Autowired
	private BookServiceImpl booksImpl;
	

	public List<Book> getAllBooks(Integer pageNo, Integer pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		 
        Page<Book> pagedResult = pagerepo.findAll(paging);
		
		//Page<Book> pagedResult = booksImpl.getAllBooks();
        if(pagedResult.hasContent()) 
        {
            return pagedResult.getContent();
        } 
        else 
        {
            return new ArrayList<Book>();
        }
	}

}
