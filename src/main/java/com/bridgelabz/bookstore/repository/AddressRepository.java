package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {

}
