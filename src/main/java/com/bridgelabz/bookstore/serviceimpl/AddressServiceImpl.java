package com.bridgelabz.bookstore.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	BCryptPasswordEncoder encoder;
	@Autowired
	private AddressRepository addressrepo;

	@Autowired
	private UserServiceImpl userimpl;
	
	@Autowired
	private UserRepository userrepo;
	@Override
	public Address addAddress(AddressDto addressDto, String token, String type) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userimpl.getUserById(userId);
		List<Address> addresses=user.getAddresses();
		Address address=new Address();
		for(Address dbAddress : addresses)
		{
			if(dbAddress.getAddressType().equals(type))
			{
				address=dbAddress; 
				address.setUpdatedtime(LocalDateTime.now());
			}
		}
		BeanUtils.copyProperties(addressDto, address);
		address.setCreatedTime(LocalDateTime.now());
		address.setAddressType(type);
		addressrepo.save(address);
		user.getAddresses().add(address);
		userrepo.save(user);
		return address;
	}

	@Override
	public Address updateAddress(AddressDto addressDto, String token, Long addressId) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userimpl.getUserById(userId);
		Address address=getAddressById(addressId, userId);
		BeanUtils.copyProperties(addressDto, address);
		address.setUpdatedtime(LocalDateTime.now());
		addressrepo.save(address);
		return address;
	}

	@Override
	public void deleteAddress(String token, Long addressId) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userimpl.getUserById(userId);
		Address address=getAddressById(addressId, userId);
		addressrepo.delete(address);
		userrepo.save(user);
	}

	@Override
	public List<Address> getAllAddresses(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userimpl.getUserById(userId);
		AddressDto addressDto=new AddressDto();
		
		return user.getAddresses();
	}

	@Override
	public Address getAddressById(Long addressId,Long userId) throws BookStoreException {
		Address address=addressrepo.getAddressById(addressId,userId).orElseThrow(()-> new BookStoreException("no address present",HttpStatus.NOT_FOUND));
		return address;
	}

	public void deleteAllAddresses(String token) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userimpl.getUserById(userId);
		addressrepo.deleteAllAddresses(userId);	
	}

	public Address getAddressForUser(String token,String type) throws BookStoreException {
		Long userId=JWTUtility.parseJWT(token);
		User user=userimpl.getUserById(userId);
		List<Address> addresses=user.getAddresses();
		for(Address address : addresses)
		{
			if(address.getAddressType().equalsIgnoreCase(type))
				return address;
		}
		return null;
	}
}
