package com.chaitu.springboot.mydiaryrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaitu.springboot.mydiaryrestapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);

}
