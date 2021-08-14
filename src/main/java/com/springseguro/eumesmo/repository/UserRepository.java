package com.springseguro.eumesmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springseguro.eumesmo.entity.User;

public interface UserRepository 
extends JpaRepository<User, Integer>
{
	 User findByEmail(String email);
	 User findByNome(String name);
}
