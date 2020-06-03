package com.bridgelabz.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.bookstore.entity.User;

public interface UserRepository extends JpaRepository<User,String> {
	
	@Query(value = "select * from user where user_id=?1",nativeQuery = true)
	Optional<User> getUserById(Long id);

	@Query(value = "select * from user where email=?1",nativeQuery = true)
	Optional<User> getUserByEmail(String email);

	@Query(value = "select * from user where email=?1",nativeQuery = true)
	Optional<User> isEmailExists(String email);
}
