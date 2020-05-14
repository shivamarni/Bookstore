package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.AddressService;
import com.bridgelabz.bookstore.utility.JWTUtility;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressrepo;

	@Autowired
	private UserServiceImpl userimpl;
	
	@Autowired
	private UserRepository userrepo;
	@Override
	public Address addAddress(AddressDto addressDto, String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userimpl.getUserById(userId);
		Address address=new Address();
		BeanUtils.copyProperties(addressDto, address);
		address.setCreatedTime(LocalDateTime.now());
		addressrepo.save(address);
		user.getAddresses().add(address);
		userrepo.save(user);
		return address;
	}

	@Override
	public Address updateAddress(AddressDto addressDto, String token, Long addressId) {
		return null;
	}

	@Override
	public void deleteAddress(String token, Long addressId) {
		
	}

	@Override
	public List<Address> getAllAddresses(String token) {
		return null;
	}

}
