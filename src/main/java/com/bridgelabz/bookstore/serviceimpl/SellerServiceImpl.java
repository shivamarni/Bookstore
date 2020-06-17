package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.ForgetPassword;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.SellerDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Seller;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.SellerRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.SellerService;
import com.bridgelabz.bookstore.utility.JWTUtility;
import com.bridgelabz.bookstore.utility.JmsUtility;

@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerRepository sellerrepo;
	@Autowired
	private BCryptPasswordEncoder pwdBcrypt;

	@Override
	@Transactional
	public Seller registerSeller(@Valid SellerDto sellerdto) throws BookStoreException {
		if (sellerrepo.getSellerByEmail(sellerdto.getEmail()).isPresent() == false) {
			Seller seller = new Seller();
			BeanUtils.copyProperties(sellerdto, seller);
			seller.setPassword(pwdBcrypt.encode(sellerdto.getPassword()));
			seller.setCreatedDate(LocalDateTime.now());
			sellerrepo.save(seller);
			JmsUtility.sendEmail(sellerdto.getEmail(), "verification email",
					"http://localhost:8085/seller/verify/" + JWTUtility.jwtToken(seller.getSellerId()));
			return seller;
		} else {

			throw new BookStoreException("email already registered", HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public String loginSeller(LoginDto dto) throws BookStoreException {
		Seller seller = getSellerByEmail(dto.getEmail());
		boolean ispwd = pwdBcrypt.matches(dto.getPassword(), seller.getPassword());
		if (ispwd == false) {
			throw new BookStoreException("incorrect password", HttpStatus.BAD_REQUEST);
		}
		return JWTUtility.jwtToken(seller.getSellerId());
	}

	@Override
	public Seller verify(String token) throws BookStoreException {

		Long sellerId = JWTUtility.parseJWT(token);

		Seller seller = getSellerById(sellerId);
		System.out.println(seller);
		seller.isVerified();
		seller.setVerified(true);
		sellerrepo.save(seller);
		return seller;
	}

	@Override
	public Seller getSellerById(Long sellerId) throws BookStoreException {
		return sellerrepo.getSellerById(sellerId)
				.orElseThrow(() -> new BookStoreException("no seller exists!!!", HttpStatus.NOT_FOUND));

	}

	public Seller forgotPassword(String email) throws BookStoreException {
		getSellerByEmail(email);
		JmsUtility.sendEmail(email, "reset your password", "http://localhost:8085/user/resetPassword/" + email);
		return null;
	}

	public Seller resetPassword(String email, ForgetPassword forgotDto) throws BookStoreException {
		Seller seller = getSellerByEmail(email);
		seller.setPassword(pwdBcrypt.encode(forgotDto.getPassword()));
		sellerrepo.save(seller);
		return seller;
	}

	@Override
	public void deleteSeller(Long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Seller> getSellers() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Seller verify(String token) throws BookStoreException {
//		Long userId=JWTUtility.parseJWT(token);
//		Seller seller=getUserById(userId);
//		seller.setVerified(true);
//		userrepo.save(seller);
//		return seller;
//	}
//
//	@Override
//	public Seller getUserById(Long userId) throws BookStoreException {
//		return userrepo.getUserById(userId).orElseThrow(() -> new BookStoreException("no user exists!!!",HttpStatus.NOT_FOUND));
//	}
//
//	@Override
//	public void deleteSeller(Long userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Seller> getSellers() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	public Seller getSellerByEmail(String email) throws BookStoreException {
		Seller seller = sellerrepo.getSellerByEmail(email)
				.orElseThrow(() -> new BookStoreException("no user exists", HttpStatus.NOT_FOUND));
		return seller;
	}
//	public boolean isEmailExists(String email) throws BookStoreException {
//		if(userrepo.isEmailExists(email).isPresent())
//			throw new BookStoreException("email already exists", HttpStatus.BAD_REQUEST);
//		return false;
//	}

	@Transactional
	public List<Book> getAllSellerBooks(String token) throws BookStoreException {
		Long sellerId = JWTUtility.parseJWT(token);

		Seller seller = getSellerById(sellerId);
		List<Book> sellerBooks=sellerrepo.getAllSellerBooks(sellerId);
		return sellerBooks;
	}

}
