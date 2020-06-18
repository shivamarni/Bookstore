package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.exception.S3BucketException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.SellerRepository;
import com.bridgelabz.bookstore.service.BookService;
import com.bridgelabz.bookstore.utility.JWTUtility;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private SellerRepository sellerrepo;

	@Autowired
	private BookServiceImpl bookService;

	@Autowired
	private BookRepository bookrepo;

	@Autowired
	private AmazonS3ClientServiceImpl amazonImpl;
	
	@Override

	@Transactional
	public Book addBook(MultipartFile file,BookDto bookDTO, String token) throws BookStoreException, S3BucketException {
		Long sellerId = JWTUtility.parseJWT(token);
		System.out.println(sellerId);
		Book book = new Book();
		BeanUtils.copyProperties(bookDTO, book);
		book.setBookAddedTime(LocalDateTime.now());
		book.setBookUpdatedTime(LocalDateTime.now());
		book=bookrepo.save(book);
		book.setBookImage(amazonImpl.uploadFileToS3Bucket(file,true, book.getBookId()));
		Seller seller = sellerrepo.getSellerById(sellerId)
				.orElseThrow(() -> new BookStoreException("Seller not found", HttpStatus.NOT_FOUND));

		boolean notExist = seller.getSellerBooks().stream()
				.noneMatch(boook -> boook.getBookName().equals(bookDTO.getBookName()));
		if (notExist) {
			System.out.println("inside if");
			seller.getSellerBooks().add(book);
			bookrepo.save(book);
			sellerrepo.save(seller);
		} else {
			throw new BookStoreException("Book already present for seller", HttpStatus.PRECONDITION_FAILED);
		}
		return book;
	}

	@Override
	@Transactional

	public Book updateBook(BookDto bookDTO, String token, Long bookId) throws BookStoreException {
		Long sellerId = JWTUtility.parseJWT(token);

		Seller seller = sellerrepo.getSellerById(sellerId)
				.orElseThrow(() -> new BookStoreException("Seller not found", HttpStatus.NOT_FOUND));

		Book book = seller.getSellerBooks().stream().filter(boook -> boook.getBookId().equals(bookId)).findFirst()
				.orElseThrow(() -> new BookStoreException("Book not present ", HttpStatus.NOT_FOUND));
		book.setBookName(bookDTO.getBookName());
		book.setBookPrice(bookDTO.getBookPrice());
		book.setBookAuthor(bookDTO.getBookAuthor());
		book.setBookDescription(bookDTO.getBookDescription());
		book.setNoOfBooks(bookDTO.getNoOfBooks());
		bookrepo.save(book);
		sellerrepo.save(seller);
		return book;

	}

	@Override
	@Transactional
	public Book deleteBook(String token, Long bookId) throws BookStoreException {

		Long sellerId = JWTUtility.parseJWT(token);

		Seller seller = sellerrepo.getSellerById(sellerId)
				.orElseThrow(() -> new BookStoreException("Seller not found", HttpStatus.NOT_FOUND));

		Book book = seller.getSellerBooks().stream().filter(boook -> boook.getBookId().equals(bookId)).findFirst()
				.orElseThrow(() -> new BookStoreException("Book not present ", HttpStatus.NOT_FOUND));
		seller.getSellerBooks().remove(book);

		bookrepo.save(book);
		sellerrepo.save(seller);
		return book;
	}

	@Override
	@Transactional
	public List<Book> getAllSellerBooks(String token) throws BookStoreException {

		Long sellerId = JWTUtility.parseJWT(token);

		Seller seller = sellerrepo.getSellerById(sellerId)
				.orElseThrow(() -> new BookStoreException("Seller not found", HttpStatus.NOT_FOUND));

		List<Book> bookList = seller.getSellerBooks();
		return bookList;
	}

	@Transactional
	@Override
	public List<Book> getBooksSortedByPriceLow(String token) throws BookStoreException {
		List<Book> bookList = bookService.getAllBooks();
		List<Book> sortBookByPriceLow = bookList.parallelStream().sorted(Comparator.comparing(Book::getBookPrice))
				.collect(Collectors.toList());
		return sortBookByPriceLow;
	}

	@Transactional
	@Override
	public List<Book> getBooksSortedByPriceHigh(String token) throws BookStoreException {
		List<Book> bookList = bookService.getAllBooks();
		List<Book> sortBookByPriceHigh = bookList.parallelStream().sorted(Comparator.comparing(Book::getBookPrice))
				.collect(Collectors.toList());
		Collections.reverse(sortBookByPriceHigh);
		return sortBookByPriceHigh;
	}

	@Transactional
	@Override
	public List<Book> getBooksSortedByArrival(String token) throws BookStoreException {
		List<Book> bookList = bookService.getAllBooks();
		List<Book> sortBookByPriceArrival = bookList.parallelStream()
				.sorted(Comparator.comparing(Book::getBookAddedTime)).collect(Collectors.toList());
		return sortBookByPriceArrival;
	}

	@Override
	@Transactional
	public Book getBookById(Long bookId) throws BookStoreException {
		Book book = bookrepo.findById(bookId)
				.orElseThrow((() -> new BookStoreException("book not found", HttpStatus.NOT_FOUND)));
		return book;
	}

	@Override
	@Transactional
	public List<Book> getAllBooks() throws BookStoreException {
		List<Book> allBooks=bookrepo.getAllBooks();
		return allBooks;
	}

	@Override
	public int getBookCount() {
		List<Book> allBooks=bookrepo.getAllBooks();
		return 	allBooks.size();
	}

	@Override
	@Transactional
	public Book verifyBook(Long bookId) throws BookStoreException {
		
		Book book = bookrepo.findById(bookId)
				.orElseThrow((() -> new BookStoreException("book not found", HttpStatus.NOT_FOUND)));

		book.setBookVerified(!book.isBookVerified());
		bookrepo.save(book);
		return book;
	}
}
