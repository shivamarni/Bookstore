package com.bridgelabz.bookstore.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.PaginationRepository;
import com.bridgelabz.bookstore.utility.JWTUtility;

@Service
public class PaginationServiceImpl {
	
	
	@Autowired
	private PaginationRepository pagerepo;
	@Autowired
	private BookServiceImpl booksImpl;
	@Autowired
	private SellerServiceImpl sellerImpl;
//	@Cacheable("books")
	public List<Book> getBooksById(Integer pageNo, Integer pageSize,String sortBy) throws BookStoreException {
		 try
	        {
	            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
	            Thread.sleep(1000*5);
	        } 
	        catch (InterruptedException e) 
	        {
	            e.printStackTrace();
	        }
	 
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));		
		Page<Book> pagedResult = pagerepo.findAll(paging);
        if(pagedResult.hasContent()) 
        {
            return pagedResult.getContent();
        } 
        else 
        {
            return new ArrayList<Book>();
        }
	}


	public List<Book> getBooksByPriceDescending(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy).descending());		
		Page<Book> pagedResult = pagerepo.findAll(paging);
        if(pagedResult.hasContent()) 
        {
            return pagedResult.getContent();
        } 
        else 
        {
            return new ArrayList<Book>();
        }
	}


	public List<Book> getBooksByPriceAscending(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy).ascending());		
		Page<Book> pagedResult = pagerepo.findAll(paging);
        if(pagedResult.hasContent()) 
        {
            return pagedResult.getContent();
        } 
        else 
        {
            return new ArrayList<Book>();
        }
	}


	public List<Book> getBooksByArrivel(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));		
		Page<Book> pagedResult = pagerepo.findAll(paging);
        if(pagedResult.hasContent()) 
        {
            return pagedResult.getContent();
        } 
        else 
        {
            return new ArrayList<Book>();
        }
	}
	
	public List<Book> getSellerBooks(Integer pageNo, Integer pageSize, String sortBy,String token) throws BookStoreException {
		Long sellerId=JWTUtility.parseJWT(token);
		Seller seller=sellerImpl.getSellerById(sellerId);
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));		
		Page<Book> pagedResult = pagerepo.findAll(sellerId,paging);
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
