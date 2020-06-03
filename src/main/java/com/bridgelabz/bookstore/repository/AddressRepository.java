package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {
	
	@Query(value="select * from address where address_id=?1 and user_id=?2",nativeQuery=true)
	Optional<Address> getAddressById(Long addressId, Long userId);
	
	@Transactional
	@Modifying
	@Query(value="delete from address where user_id=?1",nativeQuery=true)
	void deleteAllAddresses(Long userId);

}
